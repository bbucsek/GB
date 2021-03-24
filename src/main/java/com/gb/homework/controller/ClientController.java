package com.gb.homework.controller;

import com.gb.homework.exception.exceptions.WrongInputException;
import com.gb.homework.model.Client;
import com.gb.homework.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/clients")
    public UUID createClient(@RequestBody Client client) throws WrongInputException {
        return clientService.createClient(client);
    };
}
