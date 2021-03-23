package com.gb.homework.controller;

import com.gb.homework.model.Position;
import com.gb.homework.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @PostMapping("/positions")
    public String createPosition(@RequestBody Position position) {

        return positionService.createPosition(position);
    }
}
