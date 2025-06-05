package com.example.controller;

import com.example.adapter.LLMAdapterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mcp")
public class MCPController {

    private final LLMAdapterService llmAdapterService;

    public MCPController(LLMAdapterService llmAdapterService) {
        this.llmAdapterService = llmAdapterService;
    }

    @PostMapping("/query")
    public String queryLLM(@RequestBody String prompt) {
        return llmAdapterService.queryLLM(prompt);
    }
}