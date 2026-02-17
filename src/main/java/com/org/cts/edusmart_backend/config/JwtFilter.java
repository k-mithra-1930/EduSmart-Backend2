package com.org.cts.edusmart_backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "KundanMithraSaurabhSaiPranayaAyeshaVivek";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/") || // Add this
                path.startsWith("/swagger-ui/") ||
                path.startsWith("/v3/api-docs");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // DEBUG: Print to console to see if the token actually arrives from Angular
        System.out.println("DEBUG: Request URI: " + request.getRequestURI());
        System.out.println("DEBUG: Auth Header: " + (authHeader != null ? "Present" : "NULL"));

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                        .setAllowedClockSkewSeconds(60)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                String role = claims.get("role", String.class); // Get role for future permission checks

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    System.out.println("DEBUG: Authentication successful for user: " + email);
                }
            } catch (Exception e) {
                // This will tell you if the SECRET_KEY is mismatched or token expired
                System.err.println("DEBUG: JWT Validation Failed: " + e.getMessage());
            }
        } else if (authHeader == null && !request.getRequestURI().contains("/api/auth/")
                && !request.getRequestURI().contains("swagger")
                && !request.getRequestURI().contains("v3/api-docs")) {
            System.err.println("DEBUG: Missing Token for secured endpoint: " + request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }
}