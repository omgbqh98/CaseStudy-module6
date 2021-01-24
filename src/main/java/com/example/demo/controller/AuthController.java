package com.example.demo.controller;

import com.example.demo.model.GoogleToken;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //dang nhap
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateAccessToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
    }

    //dang ky tai khoan
    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg");
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Test login thành công
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    //lay User bang username
    @GetMapping("/{username}")
    public ResponseEntity<User> findUserName(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //    cap nhat mat khau
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String newPass = passwordEncoder.encode(user.getPassword());
        user.setUserId(userOptional.get().getUserId());
        user.setFullName(userOptional.get().getFullName());
        user.setPhone(userOptional.get().getPhone());
        user.setAvatar(userOptional.get().getAvatar());
        user.setUsername(userOptional.get().getUsername());
        user.setAddress(userOptional.get().getAddress());
        user.setPassword(newPass);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //API nhận Google token
    @PostMapping("/googleSignIn")
    public ResponseEntity<?> receiveGoogletoken(@RequestBody GoogleToken googleToken) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = jwtService.validateGoogleToken(googleToken.getToken());
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            User checkUser = userService.findByEmail(payload.getEmail());
            if (checkUser != null) {
                String jwt = jwtService.generateAccessToken(checkUser);
                return ResponseEntity.ok(new JwtResponse(jwt, checkUser.getUsername()));
            }
            User user = new User();
            user.setEmail(payload.getEmail());
            user.setUsername( (String)payload.get("name"));
            user.setAvatar( (String) payload.get("picture"));
            user.setFullName((String) payload.get("family_name") + (String) payload.get("given_name"));
            user.setPassword(passwordEncoder.encode("12345678"));
            user.setPhone(" ");
            userService.save(user);
            String jwt = jwtService.generateAccessToken(checkUser);
            return ResponseEntity.ok(new JwtResponse(jwt, checkUser.getUsername()));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
