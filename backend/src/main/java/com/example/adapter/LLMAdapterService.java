package com.example.adapter;

import com.example.logging.CallLoggerService;
import com.example.logging.CallRecord;
import org.slf4j.MDC;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMAdapterService {

    private final RestTemplate restTemplate;
    private final CallLoggerService callLogger;

    @Autowired
    public LLMAdapterService(CallLoggerService callLogger) {
        this.restTemplate = new RestTemplate();
        this.callLogger = callLogger;
    }

    public String queryLLM(String prompt, String provider, Map<String, Object> options) {
        long start = System.currentTimeMillis();
        CallRecord rec = new CallRecord();
        // attach trace id from the request MDC if available so we can correlate logs/calls
        try {
            String trace = MDC.get("traceId");
            if (trace != null) {
                rec.setId(trace);
            }
        } catch (Exception ignored) {
        }
        rec.setTimestamp(start);
        rec.setProvider(provider);
        rec.setPromptHash(Integer.toHexString(prompt != null ? prompt.hashCode() : 0));
        rec.setOptions(options);

        try {
            String llmEndpoint;
            switch ((provider == null) ? "" : provider.toLowerCase()) {
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

            // For now we post the prompt directly; real implementation will build provider-specific payloads.
            String response = restTemplate.postForObject(llmEndpoint, prompt, String.class);

            rec.setDurationMs(System.currentTimeMillis() - start);
            rec.setStatus("ok");
            rec.setResponseSnippet(response == null ? "" : (response.length() > 200 ? response.substring(0, 200) : response));
            callLogger.log(rec);
            return response;
        } catch (Exception e) {
            rec.setDurationMs(System.currentTimeMillis() - start);
            rec.setStatus("error");
            rec.setError(e.getMessage());
            callLogger.log(rec);
            throw e;
        }
    }
}