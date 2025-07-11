package com.example.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMAdapterService {

    private final RestTemplate restTemplate;

    public LLMAdapterService() {
        this.restTemplate = new RestTemplate();
    }

    public String queryLLM(String prompt, String provider) {
        String llmEndpoint;
        switch (provider.toLowerCase()) {
            case "openai":
                llmEndpoint = "https://api.openai.com/v1/query";
                break;
            case "anthropic":
                llmEndpoint = "https://api.anthropic.com/v1/query";
                break;
            case "drafahan":
                llmEndpoint = "https://api.drafahan.com/v1/query";
                break;
            default:
                llmEndpoint = "https://api.llmprovider.com/query";
        }
        String response = restTemplate.postForObject(llmEndpoint, prompt, String.class);
        return response;
    }
}