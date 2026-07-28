import json
from pathlib import Path
from time import time
from typing import Sequence, Any

import openai
from langchain_core.messages import message_to_dict
from langchain_core.prompt_values import PromptValue
from langchain_ollama import ChatOllama
from langchain_openai import ChatOpenAI

from utils import config, ensure_file


class ModelWrapper:
    instance = None
    model = None
    time_out_seconds = 60

    def __init__(self):
        self.generation_model_name = config.get("MODEL")
        if not self.generation_model_name:
            raise RuntimeError("MODEL is not set in .env")
        self._set_model()

    def __new__(cls, *args, **kwargs):
        if cls.instance is None:
            cls.instance = super().__new__(cls)
        return cls.instance

    def invoke(self, prompt_input: PromptValue | str | Sequence[Any], run_id: str):
        time_stamp = round(time() * 1000)
        path = Path().resolve() / f"{run_id}" / "prompt-response-pairs.jsonl"
        ensure_file(path)
        while self.time_out_seconds < int(config.get("MAX_LLM_TIMEOUT", 60)):
            try:
                response = self.model.invoke(prompt_input)
                with path.open("a", encoding="utf-8") as file:
                    file.write(json.dumps({
                        "time_stamp": time_stamp,
                        "prompt": str(prompt_input) if not isinstance(prompt_input, Sequence) else str(list(prompt_input)),
                        "response": message_to_dict(response),
                    }))
                return response
            except openai.APIConnectionError:
                self._handle_timeout()
        raise TimeoutError("Timeout exceeded maximum allowed value")

    def _set_model(self):
        if config.get("PROVIDER") == "ollama":
            self.model = ChatOllama(
                model=self.generation_model_name,
                reasoning=True,
            )
        elif config.get("PROVIDER") == "deepseek":
            if not config['DEEPSEEK_API_KEY']:
                raise RuntimeError("DEEPSEEK_API_KEY is not set in .env")
            self.model = ChatOpenAI(
                model=self.generation_model_name,
                api_key=config['DEEPSEEK_API_KEY'],
                base_url="https://api.deepseek.com",
                timeout=self.time_out_seconds,
            )
        elif config.get("PROVIDER") == "openai":
            if not config['OPENAI_API_KEY']:
                raise RuntimeError("OPENAI_API_KEY is not set in .env")
            self.model = ChatOpenAI(
                model=self.generation_model_name,
                api_key=config['OPENAI_API_KEY'],
                organization="org-ENWuCLTuWsptJcXQVIR6Exwa",
                base_url="https://api.openai.com/v1",
                timeout=self.time_out_seconds,
            )
        elif config.get("PROVIDER") == "lite_llm":
            if not config['LITE_LLM_API_KEY']:
                raise RuntimeError("LITE_LLM_API_KEY is not set in .env")
            self.model = ChatOpenAI(
                model=self.generation_model_name,
                api_key=config['LITE_LLM_API_KEY'],
                base_url="NOT_IMPLEMENTED",
                timeout=self.time_out_seconds,
            )
        else:
            raise NotImplementedError(
                "The specified model provider is not supported: {}".format(config.get("PROVIDER")))

    def _handle_timeout(self):
        print(f"[TIMEOUT]: timeout value of {self.time_out_seconds} seconds has been exceeded.")
        self.time_out_seconds += 60
        self._set_model()
