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
    @Size(min = 6, max = 8)
    private String password;
    private String fullname;
    @NotEmpty
    private String phone;
    private String address;
    private String email;
    @Column(columnDefinition="String DEFAULT 'userDefaultAvatar.jpeg'")
    private String avatar;

    public User() {
    }

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }


}