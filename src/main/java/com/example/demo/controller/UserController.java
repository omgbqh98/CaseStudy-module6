package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
//@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //dang ky tai khoan
    @PostMapping("/register")
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
        user.setAvatar("https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg");
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
