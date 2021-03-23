package com.gb.homework.service;

import com.gb.homework.model.Client;
import com.gb.homework.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Component
public class ClientService {

    private final PasswordEncoder passWordEncoder;

    public ClientService() {

        this.passWordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    ClientRepository clientRepository;

    public UUID createClient(Client client) {
        Client newClient = Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .build();

        UUID apiKey = UUID.randomUUID();

        clientRepository.save(newClient);

        return apiKey;
    };
}
