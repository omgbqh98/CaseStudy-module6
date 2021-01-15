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

    //    @PutMapping("/{id}")
//    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
//        Optional<User> userOptional = this.userService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        user.setUserId(userOptional.get().getUserId());
//        user.setFullName(userOptional.get().getFullName());
//        user.setAddress(userOptional.get().getAddress());
//        user.setEmail(userOptional.get().getEmail());
//        user.setPhone(userOptional.get().getPhone());
//
//        userService.save(user);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//cap nhat profile
    @PutMapping("/{username}")
    public ResponseEntity<User> updateProfile(@PathVariable String username, @RequestBody User user) {
        User userOptional = this.userService.findByUsername(username);
        user.setUsername(userOptional.getUsername());
        user.setPassword(userOptional.getPassword());
        user.setUserId(userOptional.getUserId());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/ownHouses")
    public ResponseEntity<Iterable<House>> findHousesByOwnerId(@PathVariable long id){
        Iterable<House> houses = houseService.findAllByOwnerIdAndDeletedFalse(id);
        return new ResponseEntity<>(houses,HttpStatus.OK);
    }
}
