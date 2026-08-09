import csv
import json
import shutil
import subprocess
import xml.etree.ElementTree as ET
from dataclasses import dataclass
from pathlib import Path
from typing import Any


project_dirs: list[str] = [
    r"C:\Users\akosc\Desktop\MAVerIT\maverit\inference_records\jackson-core\gpt-5.6-luna-jackson_core",
    r"C:\Users\akosc\Desktop\MAVerIT\maverit\inference_records\jackson-core\gpt-oss-out-jackson_core"
    # r"C:\Users\akosc\Desktop\MAVerIT\maverit\543808ba-a057-42e5-a45a-a6349c7637f8",
    # r"C:\Users\akosc\Desktop\MAVerIT\maverit\ed82290a-d2fd-48ad-9999-a7c01a453b30",
    # r"C:\Users\akosc\Desktop\MAVerIT\maverit\gpt-oss-70c9627b-a4e5-4eec-ba30-924c2949a044",
    # r"C:\Users\akosc\Desktop\MAVerIT2\ccc59237-ddd6-4ad4-b2fa-cb4cb6fe5217",
    # r"C:\Users\akosc\Desktop\MAVerIT2\ds4-3f7919bd-2ebe-4425-a9ef-09307da9f7dd",
    # r"C:\Users\akosc\Desktop\MAVerIT3\46f9e148-8beb-494c-910e-b0096cc7934c",
    # r"C:\Users\akosc\Desktop\MAVerIT3\c405569e-440f-412e-b19c-b2e3acfc7992",
    # r"C:\Users\akosc\Desktop\MAVerIT5\7e3c8571-9a96-4782-b639-965b7679fcdc",
    # r"C:\Users\akosc\Desktop\MAVerIT4\b552f65e-dd63-4077-bfa6-605eedfe0daa",
    # r"C:\Users\akosc\Desktop\MAVerIT4\ds4-f8916bee-63a0-42ae-993f-f879b17d4402",
    #     # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-cli\GPT_259c8315-826d-44aa-89f8-c64aab784351",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-cli\Minimax_05528d62-e9b6-408f-9938-afe4d1a8dc84",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-cli\Mistral_9dfe7ddd-8fee-4d47-808f-afbabea81d89",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-cli\Nemotron_1c89584b-1a82-4558-bd72-de7a4ef27511",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-codec\Minimax_3ac58a18-314e-4238-886a-c18be4d72630",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-codec\Mistral_b24977c6-7fe8-4ab8-a62f-fbddc3b717e7",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-codec\Nemotron_c6289480-394b-4b21-ac23-dd3a9cfaf64e",
    #     # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-csv\GPT_6439e11f-4256-47f6-b9b5-4a9e7d2724da",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-csv\Minimax_c8478464-089e-4a01-971f-8f09707f99c9",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-csv\Mistral_5a72a9c9-f888-40a0-90fe-408baa43a516",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\commons-csv\Nemotron_60e499ef-bf6a-494d-be4e-c36253f00d31",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\jackson-core\MiniMax_1f218354-9087-45f4-8825-86d73feba184",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\jackson-core\Mistral_cf521e50-ab76-4c08-b289-2a27de1b48ae",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\jackson-core\Nemotron_4d5d655b-2a4a-4ef3-8665-4c9ab9d2ee2e",
    #     # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\joda-time\GPT_70efd7ce-91de-44c6-9a71-1fd077217e16",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\joda-time\Minimax_bbd9b2d0-ebe0-4630-ae4f-00cff486d0f7",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\joda-time\Mistral_02760858-ff49-448c-8ffe-e97f11d1a175",
    # r"C:\Users\akosc\Desktop\MAVerIT4\inference_records\joda-time\Nemotron_9762dc11-bd9a-4e4a-a7f7-b186c1b46bf2",
]

report_csv_path = Path("report2.csv")

MAVEN_TIMEOUT_SECONDS = 60 * 60


@dataclass(frozen=True)
class RunInfo:
    run_dir: Path
    model: str
    working_directory: Path


def main() -> None:
    run_infos = [load_run_info(Path(run_dir)) for run_dir in project_dirs]
    generated_paths_by_project = collect_generated_test_paths_by_project(run_infos)

    rows = []
    for run_info in run_infos:
        improvement_metrics = analyze_improvement_metrics(run_info.run_dir)

        reset_generated_tests(run_info, generated_paths_by_project)
        reconstructed_count = reconstruct_last_compilable_tests(run_info.run_dir)

        jacoco_result = run_jacoco(run_info.working_directory)
        coverage = {}
        if jacoco_result["ok"]:
            coverage = calculate_coverage(
                find_jacoco_xml_reports(run_info.working_directory),
                ("BRANCH", "LINE", "METHOD", "CLASS"),
            )

        pitest_result = run_pitest(run_info.working_directory)
        mutation_score = ""
        test_strength = ""
        if pitest_result["ok"]:
            pitest_report_paths = find_pitest_xml_reports(run_info.working_directory)
            mutation_score = calculate_mutation_score(pitest_report_paths)
            test_strength = calculate_test_strength(pitest_report_paths)

        rows.append(
            {
                "run_dir": str(run_info.run_dir),
                "working_directory": str(run_info.working_directory),
                "model": run_info.model,
                "reconstructed_tests": reconstructed_count,
                "coverage_iteration_count": improvement_metrics["iterations"]["coverage"]["count"],
                "avg_coverage_iteration_coverage_delta": improvement_metrics["iterations"]["coverage"]["avg_coverage_delta"],
                "avg_coverage_iteration_mutation_delta": improvement_metrics["iterations"]["coverage"]["avg_mutation_delta"],
                "mutation_iteration_count": improvement_metrics["iterations"]["mutation"]["count"],
                "avg_mutation_iteration_coverage_delta": improvement_metrics["iterations"]["mutation"]["avg_coverage_delta"],
                "avg_mutation_iteration_mutation_delta": improvement_metrics["iterations"]["mutation"]["avg_mutation_delta"],
                "coverage_cycle_count": improvement_metrics["cycles"]["coverage"]["count"],
                "avg_coverage_cycle_coverage_delta": improvement_metrics["cycles"]["coverage"]["avg_coverage_delta"],
                "avg_coverage_cycle_mutation_delta": improvement_metrics["cycles"]["coverage"]["avg_mutation_delta"],
                "mutation_cycle_count": improvement_metrics["cycles"]["mutation"]["count"],
                "avg_mutation_cycle_coverage_delta": improvement_metrics["cycles"]["mutation"]["avg_coverage_delta"],
                "avg_mutation_cycle_mutation_delta": improvement_metrics["cycles"]["mutation"]["avg_mutation_delta"],
                "jacoco_ok": jacoco_result["ok"],
                "overall_branch_coverage": coverage.get("BRANCH", ""),
                "overall_line_coverage": coverage.get("LINE", ""),
                "overall_method_coverage": coverage.get("METHOD", ""),
                "overall_class_coverage": coverage.get("CLASS", ""),
                "pitest_ok": pitest_result["ok"],
                "overall_mutation_score": mutation_score,
                "overall_test_strength": test_strength,
            }
        )

    write_report(rows)


def load_run_info(run_dir: Path) -> RunInfo:
    run_dir = run_dir.resolve()
    metadata_path = run_dir / "metadata.json"
    if not metadata_path.exists():
        metadata_path = run_dir / "__metadata.json"
    metadata = read_json(metadata_path)

    model = str(metadata.get("model", ""))
    working_directory = Path(str(metadata.get("working_directory", ""))).resolve()
    if not working_directory:
        raise ValueError(f"Missing working_directory in {metadata_path}")

    return RunInfo(
        run_dir=run_dir,
        model=model,
        working_directory=working_directory,
    )


def read_json(path: Path) -> dict[str, Any]:
    with path.open("r", encoding="utf-8") as file:
        return json.load(file)


def read_jsonl(path: Path) -> list[dict[str, Any]]:
    if not path.is_file():
        return []

    records = []
    with path.open("r", encoding="utf-8") as file:
        for line_number, line in enumerate(file, start=1):
            line = line.strip()
            if not line:
                continue
            try:
                records.append(json.loads(line))
            except json.JSONDecodeError as error:
                raise ValueError(f"Invalid JSON in {path} at line {line_number}") from error
    return records


def collect_generated_test_paths_by_project(run_infos: list[RunInfo]) -> dict[Path, set[Path]]:
    paths_by_project: dict[Path, set[Path]] = {}
    for run_info in run_infos:
        project_paths = paths_by_project.setdefault(run_info.working_directory, set())
        for record in read_jsonl(run_info.run_dir / "log.jsonl"):
            for key in ("test_file_path", "last_compilable_test_file_path"):
                test_path = clean_path(record.get(key))
                if test_path is not None and is_inside(test_path, run_info.working_directory):
                    project_paths.add(test_path)
    return paths_by_project


def reset_generated_tests(run_info: RunInfo, generated_paths_by_project: dict[Path, set[Path]]) -> None:
    for test_path in sorted(generated_paths_by_project.get(run_info.working_directory, set())):
        if test_path.is_file():
            test_path.unlink()


def reconstruct_last_compilable_tests(run_dir: Path) -> int:
    last_compilable_by_path: dict[Path, str] = {}
    for record in read_jsonl(run_dir / "log.jsonl"):
        test_class = str(record.get("last_compilable_test_class") or "")
        test_path = clean_path(record.get("last_compilable_test_file_path"))
        if test_class and test_path is not None:
            last_compilable_by_path[test_path] = test_class

    for test_path, test_class in last_compilable_by_path.items():
        test_path.parent.mkdir(parents=True, exist_ok=True)
        test_path.write_text(test_class.rstrip() + "\n", encoding="utf-8")

    return len(last_compilable_by_path)


def analyze_improvement_metrics(run_dir: Path) -> dict[str, dict[str, dict[str, float | int]]]:
    records = load_successful_metric_records(run_dir)
    return {
        "iterations": analyze_iteration_improvements(records),
        "cycles": analyze_phase_cycle_improvements(records),
    }


def load_successful_metric_records(run_dir: Path) -> list[dict[str, Any]]:
    records = []
    for record in read_jsonl(run_dir / "log.jsonl"):
        if record.get("compiler_success") is not True:
            continue

        class_index = parse_int(record.get("current_class_index"))
        coverage = parse_float(record.get("coverage"))
        mutation = parse_float(record.get("mutation"))
        if class_index is None or coverage is None or mutation is None:
            continue

        phase = str(record.get("active_validation_phase") or "").strip().lower()
        if phase not in {"coverage", "mutation"}:
            continue

        records.append(
            {
                "class_index": class_index,
                "phase": phase,
                "coverage": coverage,
                "mutation": mutation,
                "coverage_iterations": parse_int(record.get("coverage_iterations")) or 0,
                "mutation_iterations": parse_int(record.get("mutation_iterations")) or 0,
            }
        )
    return records


def analyze_iteration_improvements(records: list[dict[str, Any]]) -> dict[str, dict[str, float | int]]:
    improvements = {"coverage": [], "mutation": []}
    previous_success_by_class: dict[int, dict[str, Any]] = {}
    seen_iterations_by_class = {"coverage": {}, "mutation": {}}

    for record in records:
        class_index = record["class_index"]
        phase = record["phase"]
        iteration = record[f"{phase}_iterations"]
        previous_iteration = seen_iterations_by_class[phase].get(class_index, 0)
        previous_success = previous_success_by_class.get(class_index)

        if iteration > previous_iteration and previous_success is not None:
            improvements[phase].append(metric_delta(previous_success, record))

        seen_iterations_by_class[phase][class_index] = max(previous_iteration, iteration)
        previous_success_by_class[class_index] = record

    return {phase: summarize_deltas(deltas) for phase, deltas in improvements.items()}


def analyze_phase_cycle_improvements(records: list[dict[str, Any]]) -> dict[str, dict[str, float | int]]:
    cycles = {"coverage": [], "mutation": []}
    current_block: list[dict[str, Any]] = []

    for record in records:
        if current_block and (
            record["class_index"] != current_block[-1]["class_index"]
            or record["phase"] != current_block[-1]["phase"]
        ):
            append_phase_cycle(cycles, current_block)
            current_block = []

        current_block.append(record)

    append_phase_cycle(cycles, current_block)

    return {phase: summarize_deltas(deltas) for phase, deltas in cycles.items()}


def append_phase_cycle(cycles: dict[str, list[dict[str, float]]], block: list[dict[str, Any]]) -> None:
    if not block:
        return

    cycles[block[0]["phase"]].append(metric_delta(block[0], block[-1]))


def metric_delta(previous: dict[str, Any], current: dict[str, Any]) -> dict[str, float]:
    return {
        "coverage_delta": current["coverage"] - previous["coverage"],
        "mutation_delta": current["mutation"] - previous["mutation"],
    }


def summarize_deltas(deltas: list[dict[str, float]]) -> dict[str, float | int]:
    if not deltas:
        return {
            "count": 0,
            "avg_coverage_delta": 0.0,
            "avg_mutation_delta": 0.0,
        }

    return {
        "count": len(deltas),
        "avg_coverage_delta": round(
            sum(delta["coverage_delta"] for delta in deltas) / len(deltas),
            2,
        ),
        "avg_mutation_delta": round(
            sum(delta["mutation_delta"] for delta in deltas) / len(deltas),
            2,
        ),
    }


def parse_int(value: Any) -> int | None:
    try:
        return int(value)
    except (TypeError, ValueError):
        return None


def parse_float(value: Any) -> float | None:
    try:
        return float(value)
    except (TypeError, ValueError):
        return None


def clean_path(value: Any) -> Path | None:
    if not isinstance(value, str):
        return None

    value = value.strip()
    if not value:
        return None

    return Path(value).resolve()


def is_inside(path: Path, parent: Path) -> bool:
    try:
        path.relative_to(parent)
        return True
    except ValueError:
        return False


def run_jacoco(project_dir: Path) -> dict[str, Any]:
    return run_maven(
        project_dir,
        ["clean", "test", "jacoco:report"],
    )


def run_pitest(project_dir: Path) -> dict[str, Any]:
    return run_maven(
        project_dir,
        [
            "test-compile",
            "org.pitest:pitest-maven:mutationCoverage",
            "-Dthreads=12",
            "-DoutputFormats=XML,HTML",
        ],
    )


def run_maven(project_dir: Path, args: list[str]) -> dict[str, Any]:
    mvn_path = shutil.which("mvn")
    if mvn_path is None:
        raise RuntimeError("Maven executable 'mvn' was not found on PATH.")

    result = subprocess.run(
        [mvn_path, *args],
        cwd=project_dir,
        text=True,
        capture_output=True,
        shell=False,
        timeout=MAVEN_TIMEOUT_SECONDS,
    )
    return {
        "ok": result.returncode == 0,
        "returncode": result.returncode,
        "combined_result": result.stdout + "\n" + result.stderr,
    }


def find_jacoco_xml_reports(project_dir: Path) -> list[Path]:
    return sorted(project_dir.rglob("target/site/jacoco/jacoco.xml"))


def find_pitest_xml_reports(project_dir: Path) -> list[Path]:
    reports = sorted(project_dir.rglob("target/pit-reports/**/mutations.xml"))
    if not reports:
        return []

    latest_timestamp = max(report.stat().st_mtime for report in reports)
    return [report for report in reports if report.stat().st_mtime == latest_timestamp]


def calculate_coverage(jacoco_report_paths: list[Path], counter_types: tuple[str, ...]) -> dict[str, float]:
    totals = {counter_type: {"covered": 0, "missed": 0} for counter_type in counter_types}

    for report_path in jacoco_report_paths:
        root = ET.parse(report_path).getroot()
        for counter in root.findall("counter"):
            counter_type = counter.attrib.get("type")
            if counter_type in totals:
                totals[counter_type]["covered"] += int(counter.attrib.get("covered", "0"))
                totals[counter_type]["missed"] += int(counter.attrib.get("missed", "0"))

    coverage = {}
    for counter_type, counts in totals.items():
        covered = counts["covered"]
        missed = counts["missed"]
        total = covered + missed
        coverage[counter_type] = 0.0 if total == 0 else round((covered / total) * 100, 2)

    return coverage


def calculate_mutation_score(pitest_report_paths: list[Path]) -> float:
    detected = 0
    total = 0

    for report_path in pitest_report_paths:
        root = ET.parse(report_path).getroot()
        for mutation in root.findall("mutation"):
            if mutation.attrib.get("detected", "").lower() == "true":
                detected += 1
            total += 1

    if total == 0:
        return 0.0

    return round((detected / total) * 100, 2)


def calculate_test_strength(pitest_report_paths: list[Path]) -> float:
    detected = 0
    covered = 0

    for report_path in pitest_report_paths:
        root = ET.parse(report_path).getroot()
        for mutation in root.findall("mutation"):
            status = mutation.attrib.get("status", "").upper()
            if status == "NO_COVERAGE":
                continue
            if mutation.attrib.get("detected", "").lower() == "true":
                detected += 1
            covered += 1

    if covered == 0:
        return 0.0

    return round((detected / covered) * 100, 2)


def write_report(rows: list[dict[str, Any]]) -> None:
    fieldnames = [
        "run_dir",
        "working_directory",
        "model",
        "reconstructed_tests",
        "coverage_iteration_count",
        "avg_coverage_iteration_coverage_delta",
        "avg_coverage_iteration_mutation_delta",
        "mutation_iteration_count",
        "avg_mutation_iteration_coverage_delta",
        "avg_mutation_iteration_mutation_delta",
        "coverage_cycle_count",
        "avg_coverage_cycle_coverage_delta",
        "avg_coverage_cycle_mutation_delta",
        "mutation_cycle_count",
        "avg_mutation_cycle_coverage_delta",
        "avg_mutation_cycle_mutation_delta",
        "jacoco_ok",
        "overall_branch_coverage",
        "overall_line_coverage",
        "overall_method_coverage",
        "overall_class_coverage",
        "pitest_ok",
        "overall_mutation_score",
        "overall_test_strength",
    ]

    with report_csv_path.open("w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


if __name__ == "__main__":
    main()
