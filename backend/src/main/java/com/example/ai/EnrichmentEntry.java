package com.example.ai;

public class EnrichmentEntry {
    private String requestedId;
    private String listingPreviewId;
    private AIEnrichment enrichment;

    public EnrichmentEntry() {}

    public String getRequestedId() { return requestedId; }
    public void setRequestedId(String requestedId) { this.requestedId = requestedId; }
    public String getListingPreviewId() { return listingPreviewId; }
    public void setListingPreviewId(String listingPreviewId) { this.listingPreviewId = listingPreviewId; }
    public AIEnrichment getEnrichment() { return enrichment; }
    public void setEnrichment(AIEnrichment enrichment) { this.enrichment = enrichment; }
}
