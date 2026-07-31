import json
import unittest
from pathlib import Path

from nodes.faulty_test_cleanup_node import _trim_failing_test_methods
from utils import ensure_file, get_working_directory, run_maven


class FaultyTestCleanupReplayTest(unittest.TestCase):
    def test_trimmed_logged_compiler_failures_compile(self):
        log_path = Path("gpt-oss-out") / "log.jsonl"
        if not log_path.is_file():
            self.skipTest(f"Replay log does not exist: {log_path}")

        project_dir = get_working_directory()
        replay_limit = 700
        checked_failures = 0
        skipped_failures = 0
        states = _load_compiler_failure_states(log_path)
        original_files = _snapshot_files(state["test_file_path"] for state in states)
        self.addCleanup(_restore_files, original_files)

        _remove_files(original_files)

        for state in states:
            test_class = state["test_class"]
            test_file_path = state["test_file_path"]
            line_number = state["_log_line_number"]

            with self.subTest(log_line=line_number, test_file_path=test_file_path):
                if self._assert_trimmed_failure_compiles(state, test_class, test_file_path, project_dir):
                    checked_failures += 1
                else:
                    skipped_failures += 1

            _remove_file(Path(test_file_path))
            if replay_limit is not None and checked_failures >= replay_limit:
                break

        self.assertGreater(
            checked_failures,
            0,
            f"No trim-applicable compiler failure entries were replayed. Skipped {skipped_failures} entries.",
        )

    def _assert_trimmed_failure_compiles(self, state, test_class: str, test_file_path: str, project_dir: Path) -> bool:
        test_path = Path(test_file_path)
        ensure_file(test_path)
        test_path.write_text(test_class + "\n", encoding="utf-8")

        trimmed_test_class = _trim_failing_test_methods(state, test_file_path)
        if trimmed_test_class is None:
            return False

        test_path.write_text(trimmed_test_class + "\n", encoding="utf-8")
        maven_result = run_maven(str(project_dir))

        self.assertTrue(
            _maven_compilation_succeeded(maven_result["combined_result"]),
            (
                "Maven compilation failed after trimming compiler-error test methods.\n"
                f"Test file: {test_file_path}\n\n"
                f"{maven_result['combined_result']}"
            ),
        )
        return True


def _load_compiler_failure_states(log_path: Path) -> list[dict]:
    states = []
    with log_path.open(encoding="utf-8") as log_file:
        for line_number, line in enumerate(log_file, start=1):
            state = json.loads(line)
            if state.get("compiler_success") is not False:
                continue

            test_class = state.get("test_class", "")
            test_file_path = state.get("test_file_path", "")
            if not test_class or not test_file_path:
                continue

            state["_log_line_number"] = line_number
            states.append(state)
    return states


def _snapshot_files(paths) -> dict[Path, str | None]:
    snapshot = {}
    for path in paths:
        test_path = Path(path)
        if test_path in snapshot:
            continue
        snapshot[test_path] = test_path.read_text(encoding="utf-8") if test_path.is_file() else None
    return snapshot


def _remove_files(snapshot: dict[Path, str | None]):
    for path in snapshot:
        _remove_file(path)


def _remove_file(path: Path):
    if path.is_file():
        path.unlink()


def _restore_files(snapshot: dict[Path, str | None]):
    for path, content in snapshot.items():
        if content is None:
            _remove_file(path)
        else:
            ensure_file(path)
            path.write_text(content, encoding="utf-8")


def _maven_compilation_succeeded(combined_result: str) -> bool:
    return "COMPILATION ERROR" not in combined_result


if __name__ == "__main__":
    unittest.main()
