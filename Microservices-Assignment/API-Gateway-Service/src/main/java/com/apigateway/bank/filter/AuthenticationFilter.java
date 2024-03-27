package com.apigateway.bank.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.apigateway.bank.exception.MissingAuthorizationHeaderException;
import com.apigateway.bank.exception.UnauthorizedAccessException;
import com.apigateway.bank.util.JwtUtil;

// Component to handle authentication filtering for API Gateway
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    // Constructor
    public AuthenticationFilter() {
        super(Config.class);
    }

    // Method to apply the filter
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                // Check if the Authorization header is present
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingAuthorizationHeaderException();
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                // Extract token from Authorization header
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    // Validate the JWT token
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    System.out.println("Invalid Access...!");
                    throw new UnauthorizedAccessException();
                }
            }
            // Continue with the filter chain
            return chain.filter(exchange);
        });
    }

    // Configuration class
    public static class Config {

    }
}
