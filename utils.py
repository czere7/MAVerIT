import json
import shutil
import re
import subprocess
import uuid
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Mapping, TypedDict, List
import xml.etree.ElementTree as ET
import time

from pip._internal.commands import search
from tree_sitter import Language, Parser, Query, QueryCursor
import tree_sitter_java as tsjava

from dotenv import dotenv_values
from langchain_core.messages import UsageMetadata

config = dotenv_values(".env")

PACKAGE_RE = re.compile(
    r'^\s*package\s+([A-Za-z_]\w*(?:\.[A-Za-z_]\w*)*)\s*;',
    re.MULTILINE,
)

CLASS_RE = re.compile(r'''
    ^[ \t]*
    (?:@(?!interface\b)[A-Za-z_$][\w$]*(?:\s*\([^)]*\))?\s*)*
    (?:
        (?:public|protected|private|abstract|final|static|strictfp|sealed|non-sealed)
        \s+
    )*
    (?:@interface|class|interface|enum|record)
    \s+
    ([A-Za-z_$][\w$]*)
    \b
''', re.MULTILINE | re.VERBOSE)

@dataclass
class SourceCodeFileData:
    file_path: str
    file_content: str

def write_log(prefix: str, usage_metadata: UsageMetadata, agent_state: TypedDict):
    content = json.dumps({
        "time_stamp": round(time.time() * 1000),
        "assert_less_test_amount": "",
        "prefix": prefix,
        "input_tokens": int(agent_state.get("input_tokens", 0)) + int(usage_metadata.get('input_tokens', 0)),
        "output_tokens": int(agent_state.get("output_tokens", 0)) + int(usage_metadata.get('output_tokens', 0)),
        "total_tokens": int(agent_state.get("total_tokens", 0)) + int(usage_metadata.get('total_tokens', 0)),
        "current_class_index": agent_state.get("current_class_index"),
        "test_class": agent_state.get("test_class"),
        "compiler_success": agent_state.get("compiler_success"),
        "compiler_feedback": agent_state.get("compiler_feedback"),
        "test_file_path": agent_state.get("test_file_path"),
        "last_compilable_test_class": agent_state.get("last_compilable_test_class"),
        "last_compilable_test_file_path": agent_state.get("last_compilable_test_file_path"),
        "coverage_previous": agent_state.get("coverage_previous"),
        "coverage_current": agent_state.get("coverage_current"),
        "coverage_feedback": agent_state.get("coverage_feedback"),
        "mutation_previous": agent_state.get("mutation_previous"),
        "mutation_current": agent_state.get("mutation_current"),
        "mutation_feedback": agent_state.get("mutation_feedback"),
        "active_validation_phase": agent_state.get("active_validation_phase"),
        "repair_attempts": agent_state.get("repair_attempts"),
        "coverage_iterations": agent_state.get("coverage_iterations"),
        "mutation_iterations": agent_state.get("mutation_iterations"),
    })
    write_to_log_file(content, agent_state['run_id'])

def write_compiler_log(state_update: dict, agent_state: TypedDict):
    content = {
        "time_stamp": round(time.time() * 1000),
        "current_assert_less_test_amount": count_test_without_assert(agent_state.get("test_class")),
        "input_tokens": agent_state.get("input_tokens"),
        "output_tokens": agent_state.get("output_tokens"),
        "total_tokens": agent_state.get("total_tokens"),
        "current_class_index": agent_state.get("current_class_index"),
        "test_class": agent_state.get("test_class"),
        "compiler_success": state_update.get("compiler_success"),
        "compiler_feedback": state_update.get("compiler_feedback"),
        "test_file_path": state_update.get("test_file_path"),
        "last_compilable_test_class": state_update.get("last_compilable_test_class") if state_update.get("compiler_success") else agent_state.get("last_compilable_test_class"),
        "last_compilable_test_file_path": state_update.get("last_compilable_test_file_path") if state_update.get("compiler_success") else agent_state.get("last_compilable_test_file_path"),
        "active_validation_phase": agent_state.get("active_validation_phase"),
        "repair_attempts": agent_state.get("repair_attempts"),
        "coverage_iterations": agent_state.get("coverage_iterations"),
        "mutation_iterations": agent_state.get("mutation_iterations"),
        "mutation": "None",
        "coverage": "None",
    }
    if not state_update.get('compiler_success'):
        write_to_log_file(json.dumps(content), agent_state.get("run_id"))
        return
    # common
    class_under_test = get_current_class_under_test(agent_state)
    module_dir = get_maven_module_directory(class_under_test.file_path, get_working_directory())
    # mutation score
    run_pitest(
        str(module_dir),
        get_java_fully_qualified_name(class_under_test.file_content),
        get_java_fully_qualified_name(agent_state.get("test_class", ""))
    )
    mutation_score = calculate_mutation_score(find_pitest_xml_reports(module_dir))
    # coverage
    run_jacoco(str(module_dir))
    target_class = get_java_fully_qualified_name(class_under_test.file_content)
    coverage = calculate_branch_coverage_for_class(find_jacoco_xml_reports(module_dir), target_class)
    content['mutation'] = mutation_score
    content['coverage'] = coverage
    content['overall_assert_less_test_amount'] = (
            int(agent_state.get("assert_less_test_amount", 0)) +
            count_test_without_assert(agent_state.get("test_class")))
    write_to_log_file(json.dumps(content), agent_state.get("run_id"))

def write_to_log_file(content:str, run_id: str):
    # print(json.dumps(json.loads(content), indent=2), end="")
    log_path = Path().resolve() / f"{run_id}" / "log.jsonl"
    ensure_file(log_path)
    with log_path.open("a", encoding="utf-8") as file:
        file.write(content + "\n")

def count_test_without_assert(src: str) -> int:
    junit_asserts = [
        'assertAll', 'assertArrayEquals', 'assertDoesNotThrow',
        'assertEquals', 'assertFalse',
        'assertInstanceOf', 'assertIterableEquals', 'assertLinesMatch',
        'assertNotNull', 'assertNotSame', 'assertNotEquals',
        'assertNull', 'assertSame', 'assertThat',
        'assertThrows', 'assertThrowsExactly', 'assertTimeout',
        'assertTimeoutPreemptively', 'assertTrue', 'fail']

    assertion_pattern = re.compile(
        rf"\b(?:{'|'.join(map(re.escape, junit_asserts))})\s*\("
    )
    annotated_assertion_pattern = re.compile(
        r"\s*\([^)]*\bexpected\s*=.*Exception\.class\b[^)]*\)",
        re.DOTALL,
    )

    return sum(
        (not assertion_pattern.search(test_body) and not annotated_assertion_pattern.search(test_body))
        for test_body in src.split("@Test")[1:]
    )

def is_concrete_class(data: SourceCodeFileData) -> bool:
    java_code: str = data.file_content
    expected_name = Path(data.file_path).parts[-1].replace(".java", "")
    class_pattern = re.compile(
        r'\b(public|protected|private)\s*'
        r'(final\s+)?'
        r'(?!abstract\s+)'
        r'class\s+'
        r'(\w+)',
        re.MULTILINE
    )
    search_result = class_pattern.search(java_code)
    return search_result is not None and search_result.group(len(search_result.regs)-1) is not None and search_result.group(len(search_result.regs)-1) == expected_name

def persist_checkpoint(agent_state: TypedDict, next_index: int):
    tmp_file_location = str(config.get("CLASS_INDEX_TMP_FILE", "tmp.txt"))
    state = {
        "class_index": next_index,
        "run_id": agent_state['run_id'],
        "input_tokens": agent_state['input_tokens'],
        "output_tokens": agent_state['output_tokens'],
        "total_tokens": agent_state['total_tokens'],
        "assert_less_test_amount": agent_state.get('assert_less_test_amount', 0) + count_test_without_assert(agent_state.get("test_class")),
    }
    with open(tmp_file_location, "w", encoding="UTF-8") as file:
        file.write(json.dumps(state))

def retrieve_checkpoint() -> dict:
    tmp_file_location = str(config.get("CLASS_INDEX_TMP_FILE", "tmp.txt"))
    if Path(tmp_file_location).is_file():
        with open(tmp_file_location, "r", encoding="UTF-8") as file:
            return json.loads(file.read())
    return {
        "class_index": 0,
        "run_id": str(uuid.uuid4()),
        "input_tokens": 0,
        "output_tokens": 0,
        "total_tokens": 0,
        "assert_less_test_amount": 0,
    }

def run_maven(project_dir: str):
    timeout_seconds = 120.0
    mvn_path = _get_maven_executable()
    try:
        result = subprocess.run(
            [mvn_path, "clean", "test"],
            cwd=project_dir,
            text=True,
            capture_output=True,
            shell=False,
            timeout=timeout_seconds,
        )
    except subprocess.TimeoutExpired as error:
        return {
            "ok": False,
            "combined_result": (
                f"Maven timed out after {timeout_seconds} seconds.\n"
                f"{error.stdout or ''}\n{error.stderr or ''}"
            ),
        }

    return {
        "ok": result.returncode == 0,
        "combined_result": result.stdout + "\n" + result.stderr,
    }

def _get_maven_executable() -> str:
    mvn_path = shutil.which("mvn")
    if mvn_path is None:
        raise RuntimeError("Maven executable 'mvn' was not found on PATH.")

    return mvn_path

def get_working_directory() -> Path:
    working_directory = config.get("WORKING_DIRECTORY")
    if not working_directory:
        raise RuntimeError("WORKING_DIRECTORY is not set in .env.")

    project_dir = Path(working_directory)
    if not project_dir.is_absolute():
        project_dir = Path.cwd() / project_dir

    if not project_dir.exists():
        raise RuntimeError(f"WORKING_DIRECTORY does not exist: {project_dir}")

    return project_dir

def get_maven_module_directory(source_file_path: str | Path, project_dir: str | Path | None = None) -> Path:
    project_path = Path(project_dir) if project_dir is not None else get_working_directory()
    project_path = project_path.resolve()
    current_path = Path(source_file_path).resolve()
    if current_path.is_file():
        current_path = current_path.parent

    try:
        current_path.relative_to(project_path)
    except ValueError as error:
        raise RuntimeError(f"Source file is not inside WORKING_DIRECTORY: {source_file_path}") from error

    while current_path != project_path.parent:
        if (current_path / "pom.xml").is_file():
            return current_path
        if current_path == project_path:
            break
        current_path = current_path.parent

    raise RuntimeError(f"No Maven module pom.xml found for source file: {source_file_path}")

def get_float_config(config_key: str) -> float:
    raw_value = config.get(config_key)
    if not raw_value:
        raise RuntimeError(f"{config_key} is not set in .env.")

    try:
        value = float(raw_value)
    except ValueError as error:
        raise RuntimeError(f"{config_key} must be a number.") from error

    if value < 0:
        raise RuntimeError(f"{config_key} must be greater than or equal to 0.")

    return value

def get_int_config(config_key: str) -> int:
    raw_value = config.get(config_key)
    if not raw_value:
        raise RuntimeError(f"{config_key} is not set in .env.")

    try:
        value = int(raw_value)
    except ValueError as error:
        raise RuntimeError(f"{config_key} must be an integer.") from error

    if value < 0:
        raise RuntimeError(f"{config_key} must be greater than or equal to 0.")

    return value

def run_jacoco(project_dir: str):
    mvn_path = _get_maven_executable()
    result = subprocess.run(
        [mvn_path, "clean", "test", "jacoco:report"],
        cwd=project_dir,
        text=True,
        capture_output=True,
        shell=False
    )
    return {
        "ok": result.returncode == 0,
        "combined_result": result.stdout + "\n" + result.stderr,
    }

def run_pitest(project_dir: str, target_classes: str, target_tests: str):
    mvn_path = _get_maven_executable()
    result = subprocess.run(
        [
            mvn_path,
            "test-compile",
            "org.pitest:pitest-maven:mutationCoverage",
            f"-DtargetClasses={target_classes}",
            f"-DtargetTests={target_tests}",
            "-Dthreads=12",
            "-DoutputFormats=XML,HTML",
        ],
        cwd=project_dir,
        text=True,
        capture_output=True,
        shell=False
    )
    return {
        "ok": result.returncode == 0,
        "combined_result": result.stdout + "\n" + result.stderr,
    }

def find_jacoco_xml_reports(project_dir: str | Path) -> list[Path]:
    project_path = Path(project_dir)
    return sorted(project_path.rglob("target/site/jacoco/jacoco.xml"))

def find_pitest_xml_reports(project_dir: str | Path) -> list[Path]:
    project_path = Path(project_dir)
    reports = sorted(project_path.rglob("target/pit-reports/**/mutations.xml"))
    if not reports:
        return []

    latest_timestamp = max(report.stat().st_mtime for report in reports)
    return [report for report in reports if report.stat().st_mtime == latest_timestamp]

def calculate_branch_coverage(jacoco_report_paths: list[Path]) -> float:
    covered = 0
    missed = 0

    for report_path in jacoco_report_paths:
        root = ET.parse(report_path).getroot()
        for counter in root.findall("counter"):
            if counter.attrib.get("type") == "BRANCH":
                covered += int(counter.attrib.get("covered", "0"))
                missed += int(counter.attrib.get("missed", "0"))

    total = covered + missed
    if total == 0:
        return 0.0

    return round((covered / total) * 100, 2)

def calculate_branch_coverage_for_class(jacoco_report_paths: list[Path], target_class_name: str) -> float:
    covered = 0
    missed = 0
    jacoco_class_name = target_class_name.replace(".", "/")

    for report_path in jacoco_report_paths:
        root = ET.parse(report_path).getroot()
        class_element = _find_jacoco_class(root, jacoco_class_name)
        if class_element is None:
            continue

        for counter in class_element.findall("counter"):
            if counter.attrib.get("type") == "BRANCH":
                covered += int(counter.attrib.get("covered", "0"))
                missed += int(counter.attrib.get("missed", "0"))

    total = covered + missed
    if total == 0:
        return 0.0

    return round((covered / total) * 100, 2)

def summarize_jacoco_coverage(jacoco_report_paths: list[Path], target_class_name: str) -> str:
    jacoco_class_name = target_class_name.replace(".", "/")
    summary_lines = [f"Class under test: {target_class_name}"]

    for report_path in jacoco_report_paths:
        root = ET.parse(report_path).getroot()
        class_element = _find_jacoco_class(root, jacoco_class_name)
        if class_element is None:
            continue

        class_counter_summary = _format_jacoco_counters(class_element)
        if class_counter_summary:
            summary_lines.append(f"Class counters: {class_counter_summary}")

        method_lines = _summarize_jacoco_methods(class_element)
        if method_lines:
            summary_lines.append("Methods with missed branches or lines:")
            summary_lines.extend(method_lines)

        source_lines = _summarize_jacoco_source_lines(root, class_element)
        if source_lines:
            summary_lines.append("Source lines with missed branches or instructions:")
            summary_lines.extend(source_lines)

    if len(summary_lines) == 1:
        summary_lines.append("No JaCoCo class entry was found for the class under test.")

    return "\n".join(summary_lines)

def calculate_mutation_score(pitest_report_paths: list[Path]) -> float:
    killed = 0
    total = 0
    excluded_statuses = {"NON_VIABLE", "MEMORY_ERROR", "RUN_ERROR"}

    for report_path in pitest_report_paths:
        root = ET.parse(report_path).getroot()
        for mutation in root.findall("mutation"):
            status = mutation.attrib.get("status", "").upper()
            if status in excluded_statuses:
                continue
            if status == "KILLED":
                killed += 1
            total += 1

    if total == 0:
        return 0.0

    return round((killed / total) * 100, 2)

def summarize_pitest_mutations(pitest_report_paths: list[Path], target_class_name: str) -> str:
    status_counts: dict[str, int] = {}
    actionable_mutations = []

    for report_path in pitest_report_paths:
        root = ET.parse(report_path).getroot()
        for mutation in root.findall("mutation"):
            mutated_class = _child_text_no_namespace(mutation, "mutatedClass")
            if mutated_class and mutated_class != target_class_name:
                continue

            status = mutation.attrib.get("status", "UNKNOWN").upper()
            status_counts[status] = status_counts.get(status, 0) + 1

            if status in {"SURVIVED", "NO_COVERAGE", "TIMED_OUT"}:
                actionable_mutations.append(_format_pitest_mutation(mutation, status))

    summary_lines = [f"Class under test: {target_class_name}"]
    if status_counts:
        counts = ", ".join(f"{status}: {count}" for status, count in sorted(status_counts.items()))
        summary_lines.append(f"Mutation status counts: {counts}")
    else:
        summary_lines.append("No PIT mutation entries were found for the class under test.")

    if actionable_mutations:
        summary_lines.append("Surviving or uncovered mutations to target:")
        summary_lines.extend(actionable_mutations[:20])
        if len(actionable_mutations) > 20:
            summary_lines.append(f"... {len(actionable_mutations) - 20} additional actionable mutations omitted.")

    return "\n".join(summary_lines)

def _find_jacoco_class(root: ET.Element, jacoco_class_name: str) -> ET.Element | None:
    for class_element in root.iter("class"):
        if class_element.attrib.get("name") == jacoco_class_name:
            return class_element
    return None

def _format_jacoco_counters(element: ET.Element) -> str:
    counters = []
    for counter in element.findall("counter"):
        counter_type = counter.attrib.get("type", "")
        missed = int(counter.attrib.get("missed", "0"))
        covered = int(counter.attrib.get("covered", "0"))
        total = missed + covered
        percent = 0.0 if total == 0 else round((covered / total) * 100, 2)
        counters.append(f"{counter_type} {percent:.2f}% ({covered} covered, {missed} missed)")
    return "; ".join(counters)

def _summarize_jacoco_methods(class_element: ET.Element) -> list[str]:
    method_summaries = []
    for method in class_element.findall("method"):
        method_name = method.attrib.get("name", "<unknown>")
        line_number = method.attrib.get("line", "?")
        missed_parts = []
        for counter in method.findall("counter"):
            counter_type = counter.attrib.get("type", "")
            missed = int(counter.attrib.get("missed", "0"))
            if missed > 0 and counter_type in {"BRANCH", "LINE", "INSTRUCTION"}:
                covered = int(counter.attrib.get("covered", "0"))
                missed_parts.append(f"{counter_type}: {missed} missed, {covered} covered")
        if missed_parts:
            method_summaries.append(f"- {method_name} at line {line_number}: " + "; ".join(missed_parts))
    return method_summaries

def _summarize_jacoco_source_lines(root: ET.Element, class_element: ET.Element) -> list[str]:
    source_filename = class_element.attrib.get("sourcefilename")
    if not source_filename:
        return []

    sourcefile = None
    for candidate in root.iter("sourcefile"):
        if candidate.attrib.get("name") == source_filename:
            sourcefile = candidate
            break

    if sourcefile is None:
        return []

    line_summaries = []
    for line in sourcefile.findall("line"):
        missed_branches = int(line.attrib.get("mb", "0"))
        covered_branches = int(line.attrib.get("cb", "0"))
        missed_instructions = int(line.attrib.get("mi", "0"))
        if missed_branches > 0 or missed_instructions > 0:
            line_summaries.append(
                f"- line {line.attrib.get('nr')}: "
                f"branches {covered_branches} covered/{missed_branches} missed, "
                f"instructions missed {missed_instructions}"
            )
    return line_summaries[:30]

def _format_pitest_mutation(mutation: ET.Element, status: str) -> str:
    mutated_method = _child_text_no_namespace(mutation, "mutatedMethod") or "<unknown>"
    line_number = _child_text_no_namespace(mutation, "lineNumber") or "?"
    mutator = _child_text_no_namespace(mutation, "mutator") or "<unknown mutator>"
    description = _child_text_no_namespace(mutation, "description") or "No description provided."
    return f"- {status} in {mutated_method} at line {line_number}: {mutator}; {description}"

def _child_text_no_namespace(parent: ET.Element, child_name: str) -> str:
    child = parent.find(child_name)
    return "" if child is None or child.text is None else child.text.strip()

def get_java_entity_name(java_code: str) -> str:
    class_match = CLASS_RE.search(java_code)
    if class_match is None:
        raise RuntimeError("Java code does not contain a class, interface, enum, or record declaration.")

    return class_match.group(1)

def get_java_package_name(java_code: str) -> str:
    package_match = PACKAGE_RE.search(java_code)
    if package_match is None:
        return ""

    return package_match.group(1)

def get_java_fully_qualified_name(java_code: str) -> str:
    entity_name = get_java_entity_name(java_code)
    package_name = get_java_package_name(java_code)
    if not package_name:
        return entity_name

    return f"{package_name}.{entity_name}"

def ensure_file(path: str | Path) -> Path:
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    path.touch(exist_ok=True)
    return path

def load_prompt(prompt_file_name: str) -> str:
    prompt_path = Path(__file__).resolve().parent / "prompts" / prompt_file_name
    return prompt_path.read_text(encoding="utf-8")

def format_source_files_for_prompt(source_files: list[SourceCodeFileData]) -> str:
    if not source_files:
        return "No directly referenced source files were found."

    formatted_files = []
    for index, source_file in enumerate(source_files, start=1):
        formatted_files.append(
            f"### Relevant Source File {index}\n\n"
            f"Path: `{source_file.file_path}`\n\n"
            f"```java\n{source_file.file_content}\n```"
        )

    return "\n\n".join(formatted_files)

def get_nearby_test_examples(agent_state: Mapping[str, Any], limit: int = 2) -> list[SourceCodeFileData]:
    class_under_test = get_current_class_under_test(agent_state)
    project_dir = get_working_directory()
    module_dir = get_maven_module_directory(class_under_test.file_path, project_dir)
    test_roots = [module_dir / "src" / "test" / "java", module_dir / "src" / "test"]
    package_name = get_java_package_name(class_under_test.file_content)
    package_path = Path(*package_name.split(".")) if package_name else Path()
    excluded_path = Path(agent_state.get("test_file_path", "")).resolve() if agent_state.get("test_file_path") else None

    ranked_candidates: list[Path] = []
    for test_root in test_roots:
        if not test_root.is_dir():
            continue

        package_dir = test_root / package_path
        if package_dir.is_dir():
            ranked_candidates.extend(sorted(package_dir.glob("*Test.java")))

        ranked_candidates.extend(sorted(test_root.rglob("*Test.java")))

    examples = []
    seen_paths = set()
    for candidate in ranked_candidates:
        resolved_candidate = candidate.resolve()
        if resolved_candidate in seen_paths:
            continue
        if excluded_path is not None and resolved_candidate == excluded_path:
            continue
        seen_paths.add(resolved_candidate)

        try:
            content = candidate.read_text(encoding="utf-8")
        except OSError:
            continue

        examples.append(SourceCodeFileData(str(candidate), content))
        if len(examples) >= limit:
            break

    return examples

def format_test_examples_for_prompt(test_examples: list[SourceCodeFileData]) -> str:
    if not test_examples:
        return "No nearby existing test examples were found in this Maven module."

    formatted_examples = []
    for index, test_example in enumerate(test_examples, start=1):
        formatted_examples.append(
            f"### Existing Test Example {index}\n\n"
            f"Path: `{test_example.file_path}`\n\n"
            f"```java\n{_truncate_prompt_content(test_example.file_content, 12000)}\n```"
        )

    return "\n\n".join(formatted_examples)

def _truncate_prompt_content(content: str, max_chars: int) -> str:
    if len(content) <= max_chars:
        return content

    return content[:max_chars].rstrip() + "\n// ... truncated for prompt length"

def extract_response_content(response: Any) -> str:
    content = getattr(response, "content", response)
    if isinstance(content, list):
        return "\n".join(_content_part_to_text(part) for part in content).strip()
    return str(content).strip()

def strip_markdown_code_fence(text: str) -> str:
    stripped = text.strip()
    if not stripped.startswith("```"):
        return stripped

    lines = stripped.splitlines()
    if len(lines) >= 2 and lines[-1].strip() == "```":
        return "\n".join(lines[1:-1]).strip()

    return stripped

def strip_comments_for_long_prompt(src: str) -> str:
    orig_length = len(src)
    if orig_length < 64000 * 4: # approximately 4 chars = 1 token -> only remove comments if necessary
        return src

    lines = src.splitlines()
    new_lines = []
    comment_markers = ('/**', '/*', '*', '*/', '//')
    for line in lines:
        if not line.strip().startswith('//') and '//' in line:
            index = line.find('//')
            new_lines.append(line[:index])
        elif not line.strip().startswith(comment_markers):
            new_lines.append(line)

    stripped = "\n".join(new_lines)
    print(f"[Utils] Reduced length of prompt from {orig_length} to {len(stripped)}")
    return stripped

def get_relevant_source_files(agent_state: Mapping[str, Any]) -> list[SourceCodeFileData]:
    all_files = agent_state["all_files"]
    class_under_test = get_current_class_under_test(agent_state)
    relevant_files: list[SourceCodeFileData] = []

    for source_file in all_files:
        if source_file.file_path == class_under_test.file_path:
            continue

        class_match = CLASS_RE.search(source_file.file_content)
        if not class_match or not class_match.group(1) or not class_match.group(1) in class_under_test.file_content:
            continue
        source_class_name = class_match.group(1)
        if is_concrete_class(source_file):
            compacted = compact_relevant_concrete_class(class_under_test, source_file)
            if compacted:
                print(source_class_name)
                relevant_files.append(compacted)
        if is_enum_name(source_class_name, source_file.file_content):
            print(source_class_name)
            relevant_files.append(SourceCodeFileData(source_file.file_path, source_file.file_content))
        if is_abstract_class_name(source_class_name, source_file.file_content):
            print(source_class_name)
            relevant_files.append(source_file)
            extending_class = find_extending_class(source_class_name, all_files)
            compacted_extending_class = compact_relevant_concrete_class(class_under_test, extending_class)
            if compacted_extending_class:
                x = compacted_extending_class.file_path.rfind('\\')
                print(compacted_extending_class.file_path[x:].replace(".java", ""))
                relevant_files.append(compacted_extending_class)
        if is_interface_name(source_class_name, source_file.file_content):
            print(source_class_name)
            relevant_files.append(source_file)
            implementing_class = find_implementing_class(source_class_name, all_files)
            compacted_implementing_class = compact_relevant_concrete_class(class_under_test, implementing_class)
            if compacted_implementing_class:
                x = compacted_implementing_class.file_path.rfind('\\')
                print(compacted_implementing_class.file_path[x:].replace(".java", ""))
                relevant_files.append(compacted_implementing_class)

    return deduplicate_source_code_file_data(relevant_files)

def is_enum_name(source_class_name: str, source_code_content: str) -> bool:
    return re.compile(r"\benum " + source_class_name + r"\b").search(source_code_content) is not None

def is_abstract_class_name(source_class_name: str, source_code_content: str) -> bool:
    return re.compile(r"\babstract class " + source_class_name + r"\b").search(source_code_content) is not None

def is_interface_name(source_class_name: str, source_code_content: str) -> bool:
    return re.compile(r"\binterface " + source_class_name + r"\b").search(source_code_content) is not None

def find_extending_class(source_class_name: str, all_files: list[SourceCodeFileData]) -> SourceCodeFileData | None:
    for source_file in all_files:
        if re.compile(r"\bextends " + source_class_name + r"\b").search(source_file.file_content):
            return source_file
    return None

def find_implementing_class(source_class_name: str, all_files: list[SourceCodeFileData]) -> SourceCodeFileData | None:
    for source_file in all_files:
        if re.compile(r"\bimplements " + source_class_name + r"\b").search(source_file.file_content):
            return source_file
    return None

def compact_relevant_concrete_class(class_under_test: SourceCodeFileData | None, source_file: SourceCodeFileData | None) -> SourceCodeFileData | None:
        if not class_under_test or not source_file:
            return None
        methods = extract_methods(source_file.file_content)
        snippets = []
        for method in methods:
            if method.get("name") in class_under_test.file_content and method.get("source"):
                snippets.append(method.get("source"))
        if snippets:
            return SourceCodeFileData(source_file.file_path, "\n...\n".join(snippets))
        return None

def deduplicate_source_code_file_data(data: List[SourceCodeFileData]) -> List[SourceCodeFileData]:
    cache = set()
    deduplicated_list: List[SourceCodeFileData] = []
    for source_file in data:
        if source_file.file_path not in cache:
            cache.add(source_file.file_path)
            deduplicated_list.append(source_file)
    return deduplicated_list

def node_text(source: bytes, node):
    return source[node.start_byte:node.end_byte].decode("utf-8")

JAVA_LANGUAGE = Language(tsjava.language())
parser = Parser(JAVA_LANGUAGE)

def walk(node):
    yield node

    for child in node.children:
        yield from walk(child)

def extract_methods(java_code: str):
    source = java_code.encode("utf-8")
    tree = parser.parse(source)

    results = []

    for node in walk(tree.root_node):
        if node.type not in {"method_declaration", "constructor_declaration"}:
            continue

        name_node = node.child_by_field_name("name")
        if name_node is None:
            continue

        results.append({
            "kind": "constructor" if node.type == "constructor_declaration" else "method",
            "name": node_text(source, name_node),
            "source": node_text(source, node),
            "line": node.start_point.row + 1,
        })

    return results

def get_current_class_under_test(agent_state: Mapping[str, Any]) -> SourceCodeFileData:
    all_test_files = agent_state["all_test_files"]
    current_class_index = agent_state["current_class_index"]
    if current_class_index < 0 or current_class_index >= len(all_test_files):
        raise IndexError(f"current_class_index out of range: {current_class_index}")

    return all_test_files[current_class_index]

def initial_metric_state() -> dict[str, Any]:
    return {
        "test_class": "",
        "compiler_success": False,
        "compiler_feedback": "",
        "test_file_path": "",
        "last_compilable_test_class": "",
        "last_compilable_test_file_path": "",
        "coverage_previous": 0.0,
        "coverage_current": 0.0,
        "coverage_delta": 0.0,
        "coverage_improved_significantly": False,
        "coverage_feedback": "",
        "mutation_previous": 0.0,
        "mutation_current": 0.0,
        "mutation_delta": 0.0,
        "mutation_improved_significantly": False,
        "mutation_feedback": "",
        "active_validation_phase": "coverage",
        "repair_attempts": 0,
        "coverage_iterations": 0,
        "mutation_iterations": 0,
    }

def reset_state_for_current_class() -> dict[str, Any]:
    return initial_metric_state()

def advance_to_next_class(agent_state: Mapping[str, Any], new_runtime: float) -> dict[str, Any]:
    next_index = agent_state["current_class_index"] + 1
    return {
        "current_class_index": next_index,
        "assert_less_test_amount": agent_state.get('assert_less_test_amount', 0) + count_test_without_assert(agent_state.get("test_class")),
        "runtime": new_runtime + agent_state.get("runtime", 0),
        **reset_state_for_current_class(),
    }

def _content_part_to_text(part: Any) -> str:
    if isinstance(part, str):
        return part
    if isinstance(part, dict):
        return str(part.get("text", ""))
    return str(part)
