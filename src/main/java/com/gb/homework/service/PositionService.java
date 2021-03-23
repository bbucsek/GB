package com.gb.homework.service;

import com.gb.homework.model.Position;
import com.gb.homework.model.credentials.PositionCredentials;
import com.gb.homework.repository.KeyRepository;
import com.gb.homework.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    KeyRepository keyRepository;

    public String createPosition(PositionCredentials positionCredentials) throws Error{

        keyRepository
                .findById(positionCredentials.getApiKey())
                .orElseThrow(() -> new Error("not good"));

        Position newPosition = Position.builder()
                .jobTitle(positionCredentials.getJobTitle())
                .location(positionCredentials.getLocation())
                .build();

        positionRepository.save(newPosition);
        return "localhost:8080/positions/" + newPosition.getId();
    }
}
