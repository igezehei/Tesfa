package com.example.adapter;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMAdapterService {

    private final RestTemplate restTemplate;

    public LLMAdapterService() {
        this.restTemplate = new RestTemplate();
    }

    public String queryLLM(String prompt, String provider, Map<String, Object> options) {
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
        // Example: Use options for provider-specific request customization
        // (e.g., add temperature, maxTokens, etc. to the request)
        // For brevity, just send the prompt
        String response = restTemplate.postForObject(llmEndpoint, prompt, String.class);
        return response;
    }
}