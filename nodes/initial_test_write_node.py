from time import time
from typing import TYPE_CHECKING

from models import ModelWrapper
from utils import (
    extract_response_content,
    format_source_files_for_prompt,
    format_test_examples_for_prompt,
    get_current_class_under_test,
    get_nearby_test_examples,
    get_relevant_source_files,
    load_prompt,
    strip_markdown_code_fence, write_log, strip_comments_for_long_prompt,
)

if TYPE_CHECKING:
    from AgentState import AgentState


def initial_test_write_node(agent_state: "AgentState") -> dict[str, str]:
    class_under_test = get_current_class_under_test(agent_state)
    print(
        f"[initial_test_write_node] Generating initial test for "
        f"{agent_state.get('current_class_index', 0) + 1}/{len(agent_state.get('all_test_files', []))}: "
        f"{class_under_test.file_path}"
    )
    prompt = _build_prompt(agent_state)
    response = ModelWrapper().invoke(prompt, agent_state.get("run_id", ""))
    input_tokens = response.usage_metadata.get('input_tokens')
    output_tokens = response.usage_metadata.get('output_tokens')
    test_class = strip_markdown_code_fence(extract_response_content(response))

    if not test_class:
        raise RuntimeError("Initial test writer model returned an empty response.")

    # write_log(f"[initial_test_write_node] Generated initial test class.", response.usage_metadata, agent_state)

    return {
        "test_class": test_class,
        "active_validation_phase": "coverage",
        "repair_attempts": 0,
        "input_tokens": input_tokens + agent_state.get('input_tokens', 0),
        "output_tokens": output_tokens + agent_state.get('output_tokens', 0),
        "total_tokens": input_tokens + output_tokens + agent_state.get('total_tokens', 0),
        "current_test_start_time": time()
    }


def _build_prompt(agent_state: "AgentState") -> str:
    class_under_test = get_current_class_under_test(agent_state)
    relevant_source_files = get_relevant_source_files(agent_state)
    nearby_test_examples = get_nearby_test_examples(agent_state)
    prompt_template = load_prompt("initial_test_write_prompt.md")

    return strip_comments_for_long_prompt(prompt_template.format(
        class_path=class_under_test.file_path,
        class_under_test=class_under_test.file_content,
        relevant_source_files=format_source_files_for_prompt(relevant_source_files),
        # nearby_test_examples=format_test_examples_for_prompt(nearby_test_examples),
    ))
