import csv
import json
import shutil
import subprocess
import xml.etree.ElementTree as ET
from dataclasses import dataclass
from pathlib import Path
from typing import Any


# Fill this with absolute paths to run directories, for example:
# project_dirs = [
#     r"C:\Users\akosc\Desktop\MAVerIT4\ds4-f8916bee-63a0-42ae-993f-f879b17d4402",
# ]
project_dirs: list[str] = [r"C:\Users\akosc\Desktop\MAVerIT4\ds4-f8916bee-63a0-42ae-993f-f879b17d4402"]

report_csv_path = Path("report.csv")

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
