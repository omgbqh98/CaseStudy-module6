package com.example.demo.model;

import lombok.Data;

@Data
public class GoogleToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
