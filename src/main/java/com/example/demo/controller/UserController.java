package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")

public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //lay User bang username
    @GetMapping("/{username}")
    public ResponseEntity<User> findUserName(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // Cập nhật thông tin user
    @PutMapping("/{username}")
    public ResponseEntity<User> updateProfile(@PathVariable String username, @RequestBody User user) {
        User userOptional = this.userService.findByUsername(username);
        user.setUsername(userOptional.getUsername());
        user.setPassword(userOptional.getPassword());
        user.setUserId(userOptional.getUserId());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
