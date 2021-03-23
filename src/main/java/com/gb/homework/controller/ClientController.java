package com.gb.homework.controller;

import com.gb.homework.model.Client;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClientController {

    @PostMapping("/clients")
    public UUID createClient(@RequestBody Client client) {
        return UUID.randomUUID();
    };
}
