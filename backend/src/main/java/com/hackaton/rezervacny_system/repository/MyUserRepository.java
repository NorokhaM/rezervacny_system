package com.hackaton.rezervacny_system.repository;

import com.hackaton.rezervacny_system.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByEmail(String email);
}
