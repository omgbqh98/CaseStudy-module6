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
@RequestMapping("users")
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

    //dang ky tai khoan
    @PostMapping()
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg");
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setUsername(userOptional.get().getUsername());
        user.setPassword(userOptional.get().getPassword());
        user.setUserId(userOptional.get().getUserId());
//        user.setFullName(userOptional.get().getFullName());
//        user.setAddress(userOptional.get().getAddress());
//        user.setEmail(userOptional.get().getEmail());
//        user.setPhone(userOptional.get().getPhone());

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}