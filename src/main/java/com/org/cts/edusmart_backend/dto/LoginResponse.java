package com.org.cts.edusmart_backend.dto;

import java.util.List;


public record LoginResponse(String token, String email, List<String> roles) {}