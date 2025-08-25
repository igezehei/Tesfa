package com.example.ai;

import java.util.List;

public class EnrichmentReport {
    private String taskId;
    private List<EnrichmentEntry> entries;

    public EnrichmentReport() {}

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    public List<EnrichmentEntry> getEntries() { return entries; }
    public void setEntries(List<EnrichmentEntry> entries) { this.entries = entries; }
}
