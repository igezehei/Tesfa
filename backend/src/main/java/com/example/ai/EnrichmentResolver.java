package com.example.ai;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EnrichmentResolver implements GraphQLQueryResolver {

    /**
     * Request AI enrichments for a listing previews creation task.
     * This is a non-destructive analysis: it does not modify existing data, only returns suggestions.
     */
    public EnrichmentReport enrichListingPreviews(String taskId) {
        EnrichmentReport r = new EnrichmentReport();
        r.setTaskId(taskId);
        r.setEntries(Collections.emptyList());
        return r;
    }
}
