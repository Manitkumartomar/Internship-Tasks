package com.apigateway.bank.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

// Component to validate routes for API Gateway
@Component
public class RouteValidator {

        // List of open API endpoints that do not require authentication
        public static final List<String> openApiEndpoints = List.of(
                        "/auth/register",
                        "/auth/token",
                        "/eureka");

        // Predicate to determine if a route is secured (requires authentication)
        public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
                        .stream()
                        // Check if the request path contains any of the open API endpoints
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
