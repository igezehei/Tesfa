package com.example.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestTracingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString();
        try {
            MDC.put("traceId", traceId);
            if (response instanceof HttpServletResponse) {
                ((HttpServletResponse) response).setHeader("X-Trace-Id", traceId);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove("traceId");
        }
    }
}
