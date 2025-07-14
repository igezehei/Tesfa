package com.example.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.adapter.LLMAdapterService;
import org.springframework.stereotype.Component;

@Component
public class LLMQueryResolver implements GraphQLQueryResolver {
    private final LLMAdapterService llmAdapterService;

    public LLMQueryResolver(LLMAdapterService llmAdapterService) {
        this.llmAdapterService = llmAdapterService;
    }

    public String queryLLM(String prompt, String provider) {
        return llmAdapterService.queryLLM(prompt, provider);
    }
}
