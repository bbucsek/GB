package com.gb.homework.service;

import com.gb.homework.model.Position;
import com.gb.homework.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    public String createPosition(Position position) {

        Position newPosition = Position.builder()
                .jobTitle(position.getJobTitle())
                .location(position.getLocation())
                .build();

        positionRepository.save(newPosition);
        return "localhost:8080/positions/" + newPosition.getId();
    }
}
