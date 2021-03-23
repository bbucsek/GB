package com.gb.homework.controller;

import com.gb.homework.model.Client;
import com.gb.homework.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

@RestController
public class ClientController {

    private final PasswordEncoder passWordEncoder;

    @Autowired
    ClientService clientService;

    public ClientController() {

        this.passWordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @PostMapping("/clients")
    public UUID createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    };
}
