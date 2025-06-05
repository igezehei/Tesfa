package com.example.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMAdapterService {

    private final RestTemplate restTemplate;

    public LLMAdapterService() {
        this.restTemplate = new RestTemplate();
    }

    public String queryLLM(String prompt) {
        String llmEndpoint = "https://api.llmprovider.com/query"; // Replace with actual LLM endpoint
        String response = restTemplate.postForObject(llmEndpoint, prompt, String.class);
        return response;
    }
}