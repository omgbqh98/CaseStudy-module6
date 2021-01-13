package com.example.demo.service;

//import org.springframework.security.core.userdetails.User;
import com.example.demo.model.User;

import java.util.Optional;

public interface IUserService  {
    void save(User user);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

//    boolean isRegister(User user);

//    UserDetails loadUserById(Long id);

//    User findByUsername(String username);


}
