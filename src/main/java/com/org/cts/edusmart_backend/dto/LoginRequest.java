package com.org.cts.edusmart_backend.dto;


import lombok.Data;

@Data
public class LoginRequest {
    //private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
