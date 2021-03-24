package com.gb.homework.controller;

import com.gb.homework.model.Position;
import com.gb.homework.model.credentials.PositionCredentials;
import com.gb.homework.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable UUID id) {

        return positionService.getPosition(id);
    };

    @PostMapping()
    public String createPosition(@RequestBody PositionCredentials positionCredentials) throws Error {

        return positionService.createPosition(positionCredentials);
    }

    @GetMapping()
    public Set<Position> searchPositions(
            @RequestParam(required = false)
                    String title,
                    String location,
            @RequestParam UUID apiKey) {

        return positionService.searchPositions(title, location, apiKey);
    }
}
