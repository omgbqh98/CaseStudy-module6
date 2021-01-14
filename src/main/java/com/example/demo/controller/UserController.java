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
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<User> findCustomer(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return userService.findById(user.get().getUserId());
    }

    //cap nhat profile
    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setUsername(userOptional.get().getUsername());
        user.setPassword(userOptional.get().getPassword());
        user.setUserId(userOptional.get().getUserId());

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
