package com.example.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.adapter.LLMAdapterService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.HashMap;

@Component
public class LLMQueryResolver implements GraphQLQueryResolver {
    private final LLMAdapterService llmAdapterService;

    public LLMQueryResolver(LLMAdapterService llmAdapterService) {
        this.llmAdapterService = llmAdapterService;
    }

    /**
     * Query an LLM provider (OpenAI, Anthropic, Drafahan, etc.) with a prompt.
     * This method is designed to be extensible for future AI/LLM integrations.
     * @param prompt The user prompt or question.
     * @param provider The LLM provider to use (e.g., "openai", "anthropic", "drafahan").
     * @return The LLM's response as a string.
     */
    public String queryLLM(String prompt, String provider) {
        // Future: Add support for provider-specific options, streaming, or agentic context.
    return llmAdapterService.queryLLM(prompt, provider, new HashMap<>());
    }

    /**
     * Query an LLM provider with provider-specific options.
     * @param prompt The user prompt or question.
     * @param provider The LLM provider to use.
     * @param options Provider-specific options (e.g., temperature, maxTokens).
     * @return The LLM's response as a string.
     */
    public String queryLLM(String prompt, String provider, Map<String, Object> options) {
        // Example: Pass options to the adapter for provider-specific handling
        return llmAdapterService.queryLLM(prompt, provider, options);
    }
}

@Component
public class LLMWebSocketHandler extends TextWebSocketHandler {
    private final LLMAdapterService llmAdapterService;

    public LLMWebSocketHandler(LLMAdapterService llmAdapterService) {
        this.llmAdapterService = llmAdapterService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Expecting message as JSON: {"prompt": "...", "provider": "...", "options": {...}}
        String payload = message.getPayload();
        // For brevity, use simple parsing (replace with proper JSON parsing in production)
        String prompt = payload.replaceAll(".*\\"prompt\\":\\"(.*?)\\".*", "$1");
        String provider = payload.replaceAll(".*\\"provider\\":\\"(.*?)\\".*", "$1");
        // TODO: Parse options from JSON
        Map<String, Object> options = new HashMap<>();
        String response = llmAdapterService.queryLLM(prompt, provider, options);
        session.sendMessage(new TextMessage(response));
    }
}
