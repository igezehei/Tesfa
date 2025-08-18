package com.example.controller;

import com.example.logging.CallLoggerService;
import com.example.logging.CallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

@Component
public class RecentCallsResolver implements GraphQLQueryResolver {

    private final CallLoggerService callLoggerService;

    @Autowired
    public RecentCallsResolver(CallLoggerService callLoggerService) {
        this.callLoggerService = callLoggerService;
    }

    public List<CallRecord> recentCalls(Integer limit) {
        int l = (limit == null) ? 50 : limit;
        return callLoggerService.recent(l);
    }
}
