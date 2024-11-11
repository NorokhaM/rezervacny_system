package com.hackaton.rezervacny_system.service;

import com.hackaton.rezervacny_system.model.Playground;
import com.hackaton.rezervacny_system.repository.PlaygroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaygroundService {

    private final PlaygroundRepository playgroundRepository;

    @Autowired
    public PlaygroundService(PlaygroundRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }


    public Playground addPlayground(Playground playground){
        return playgroundRepository.save(playground);
    }

    public List<Playground> getPlaygrounds(){
        return playgroundRepository.findAll();
    }

    public Playground getPlaygroundById(Long id) {
        return playgroundRepository
                .findById(id)
                .orElse(null);
    }

    public Long getPlaygroundIdByName(String name) {
        return playgroundRepository
                .findByName(name)
                .getId();
    }
}
