package com.hackaton.rezervacny_system.controller;


import com.hackaton.rezervacny_system.model.Playground;
import com.hackaton.rezervacny_system.service.PlaygroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playground")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @Autowired
    public PlaygroundController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPlayground(@RequestBody Playground playground){
        try {
            return ResponseEntity.ok(playgroundService.addPlayground(playground));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Playground already exists");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Playground>> getAllPlaygrounds(){
        return ResponseEntity.ok(playgroundService.getPlaygrounds());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Playground> getPlaygroundById(@PathVariable Long id){
        return ResponseEntity.ok(playgroundService.getPlaygroundById(id));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Long> getPlaygroundIdByName(@PathVariable String name){
        return ResponseEntity.ok(playgroundService.getPlaygroundIdByName(name));
    }
}
