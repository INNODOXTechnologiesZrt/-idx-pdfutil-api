package com.innodox.pdfutilapi.rest.requestfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

// unused
//@Component
public class RequestIdFilter /*extends OncePerRequestFilter*/ {

    private static final String REQUEST_ID_PARAM = "requestId";  // The name of the request parameter
    private static final String MDC_REQUEST_ID_KEY = "requestId";  // Key for MDC

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        try {
//            // Extract requestId from the request parameter
//            String requestId = request.getParameter(REQUEST_ID_PARAM);
//
//            if (requestId == null || requestId.isEmpty()) {
//                // Optionally generate a UUID if no requestId is provided in the request
//                requestId = UUID.randomUUID().toString();
//            }
//
//            // Put requestId into the MDC for logging
//            MDC.put(MDC_REQUEST_ID_KEY, requestId);
//
//            // Proceed with the rest of the filter chain
//            filterChain.doFilter(request, response);
//
//        } finally {
//            // Clean up the MDC after the request is processed to avoid leakage
//            MDC.remove(MDC_REQUEST_ID_KEY);
//        }
//    }
}
