package com.example.logging;

import java.util.Map;

public class CallRecord {
    private String id;
    private long timestamp;
    private String provider;
    private String promptHash;
    private Map<String, Object> options;
    private String responseSnippet;
    private long durationMs;
    private String status;
    private String error;

    public CallRecord() {}

    public CallRecord(String id, long timestamp, String provider, String promptHash, Map<String, Object> options, String responseSnippet, long durationMs, String status, String error) {
        this.id = id;
        this.timestamp = timestamp;
        this.provider = provider;
        this.promptHash = promptHash;
        this.options = options;
        this.responseSnippet = responseSnippet;
        this.durationMs = durationMs;
        this.status = status;
        this.error = error;
    }

    public String getId() { return id; }
    public long getTimestamp() { return timestamp; }
    public String getProvider() { return provider; }
    public String getPromptHash() { return promptHash; }
    public Map<String, Object> getOptions() { return options; }
    public String getResponseSnippet() { return responseSnippet; }
    public long getDurationMs() { return durationMs; }
    public String getStatus() { return status; }
    public String getError() { return error; }

    public void setId(String id) { this.id = id; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public void setProvider(String provider) { this.provider = provider; }
    public void setPromptHash(String promptHash) { this.promptHash = promptHash; }
    public void setOptions(Map<String, Object> options) { this.options = options; }
    public void setResponseSnippet(String responseSnippet) { this.responseSnippet = responseSnippet; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }
    public void setStatus(String status) { this.status = status; }
    public void setError(String error) { this.error = error; }
}
