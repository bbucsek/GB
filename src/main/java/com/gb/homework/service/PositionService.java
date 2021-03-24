package com.gb.homework.service;

import com.gb.homework.model.Position;
import com.gb.homework.model.credentials.PositionCredentials;
import com.gb.homework.repository.KeyRepository;
import com.gb.homework.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    KeyRepository keyRepository;

    private final String API_URL = "https://jobs.github.com/positions.json?";

    public String createPosition(PositionCredentials positionCredentials) throws Error{

        keyRepository
                .findById(positionCredentials.getApiKey())
                .orElseThrow(() -> new Error("not good"));

        Position newPosition = Position.builder()
                .description(positionCredentials.getDescription())
                .location(positionCredentials.getLocation())
                .build();

        positionRepository.save(newPosition);
        return "localhost:8080/positions/" + newPosition.getId();
    }

    public Position getPosition(UUID id) {

        return positionRepository.findById(id).get();
    }

    public Set<Position> searchPositions(String description, String location, UUID apiKey) throws Error {

        keyRepository
                .findById(apiKey)
                .orElseThrow(() -> new Error("not good"));

        return positionRepository.getPositionByKeywordAndLocation(description, location);
    }
}
