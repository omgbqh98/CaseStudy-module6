package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String fullName;
    @NotEmpty
    private String phone;
    private String address;
    private String email;
    private String avatar;

    public User() {
    }

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }


}
