package com.hackaton.rezervacny_system.repository;

import com.hackaton.rezervacny_system.model.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    Playground findByName(String name);
}
