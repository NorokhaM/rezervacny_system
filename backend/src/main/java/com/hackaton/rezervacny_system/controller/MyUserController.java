package com.hackaton.rezervacny_system.controller;


import com.hackaton.rezervacny_system.model.MyUser;
import com.hackaton.rezervacny_system.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/user")
@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/get")
    public ResponseEntity<String> getUser(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username){
        Long userId = myUserService.findIdByUsername(username);
        return ResponseEntity.ok(userId);
    }
}
