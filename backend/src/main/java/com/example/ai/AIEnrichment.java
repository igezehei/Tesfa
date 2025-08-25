package com.example.ai;

import java.util.List;

public class AIEnrichment {
    private String titleSuggestion;
    private String descriptionSuggestion;
    private Double qualityScore;
    private String categorySuggestion;
    private List<String> aspectSuggestions;
    private List<String> imageAltTexts;
    private List<String> moderationFlags;
    private long generatedAt;
    private String model;

    public AIEnrichment() {}

    // getters and setters
    public String getTitleSuggestion() { return titleSuggestion; }
    public void setTitleSuggestion(String titleSuggestion) { this.titleSuggestion = titleSuggestion; }
    public String getDescriptionSuggestion() { return descriptionSuggestion; }
    public void setDescriptionSuggestion(String descriptionSuggestion) { this.descriptionSuggestion = descriptionSuggestion; }
    public Double getQualityScore() { return qualityScore; }
    public void setQualityScore(Double qualityScore) { this.qualityScore = qualityScore; }
    public String getCategorySuggestion() { return categorySuggestion; }
    public void setCategorySuggestion(String categorySuggestion) { this.categorySuggestion = categorySuggestion; }
    public List<String> getAspectSuggestions() { return aspectSuggestions; }
    public void setAspectSuggestions(List<String> aspectSuggestions) { this.aspectSuggestions = aspectSuggestions; }
    public List<String> getImageAltTexts() { return imageAltTexts; }
    public void setImageAltTexts(List<String> imageAltTexts) { this.imageAltTexts = imageAltTexts; }
    public List<String> getModerationFlags() { return moderationFlags; }
    public void setModerationFlags(List<String> moderationFlags) { this.moderationFlags = moderationFlags; }
    public long getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(long generatedAt) { this.generatedAt = generatedAt; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}
