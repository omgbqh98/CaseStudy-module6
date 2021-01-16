package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.User;
import com.example.demo.service.house.IHouseService;
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
    IHouseService houseService;

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

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.findByUsername(user.getUsername());
        user1.setFullName(user.getFullName());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        userService.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Tìm tất cả nhà của một chủ nhà
    @GetMapping("/{id}/ownHouses")
    public ResponseEntity<Iterable<House>> findHousesByOwnerId(@PathVariable long id){
        Iterable<House> houses = houseService.findAllByOwnerIdAndDeletedFalse(id);
        return new ResponseEntity<>(houses,HttpStatus.OK);
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
