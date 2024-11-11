package com.hackaton.rezervacny_system.service;


import com.hackaton.rezervacny_system.model.MyUser;
import com.hackaton.rezervacny_system.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserService {
    private final AuthenticationManager authenticationManager;
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Autowired
    public MyUserService(MyUserRepository myUserRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder=passwordEncoder;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public MyUser registerUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }

    public String verifyUser(MyUser user) {
        Authentication authentication=
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword())
                );
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "User is not authenticated";
    }

    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    public Optional<MyUser> findByEmail(String email) {
        return myUserRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return myUserRepository.findByEmail(email).isPresent();
    }

    public boolean existsByUsername(String username) {
        return myUserRepository.findByUsername(username).isPresent();
    }

    public void findByEmailAndSetUsername(MyUser user) {
        myUserRepository
                .findByEmail(user.getEmail())
                .ifPresent(foundUser -> user.setUsername(foundUser.getUsername()));
    }
}
