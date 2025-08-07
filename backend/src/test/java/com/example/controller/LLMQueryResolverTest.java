package com.example.controller;

import com.example.adapter.LLMAdapterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LLMQueryResolverTest {
    private LLMAdapterService llmAdapterService;
    private LLMQueryResolver resolver;

    @BeforeEach
    void setUp() {
        llmAdapterService = mock(LLMAdapterService.class);
        resolver = new LLMQueryResolver(llmAdapterService);
    }

    @Test
    void testQueryLLM() {
        when(llmAdapterService.queryLLM("hello", "openai")).thenReturn("response");
        String result = resolver.queryLLM("hello", "openai");
        assertEquals("response", result);
        verify(llmAdapterService).queryLLM("hello", "openai");
    }
}
