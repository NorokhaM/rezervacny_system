package com.hackaton.rezervacny_system.controller;


import com.hackaton.rezervacny_system.model.MyUser;
import com.hackaton.rezervacny_system.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) {
        String email=user.getEmail();
        String username=user.getUsername();
        if (myUserService.existsByEmail(email) ||
                myUserService.existsByUsername(username)) {
            return ResponseEntity.status(409).body("User already exists");
        }
        return ResponseEntity.ok(myUserService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody MyUser user) {
        myUserService.findByEmailAndSetUsername(user);
        return ResponseEntity.ok(myUserService.verifyUser(user));
    }

    @GetMapping("/get")
        public ResponseEntity<List<MyUser>> getAllUsers(){
            return ResponseEntity.ok(myUserService.findAll());
        }
    }
