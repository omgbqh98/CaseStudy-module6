package com.example.demo.model;

import lombok.Data;

@Data
public class JwtResponse {
    private Long userId;
    private String token;
    private String type = "Bearer";
    private String username;

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
