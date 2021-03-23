package com.gb.homework.service;

import com.gb.homework.model.Client;
import com.gb.homework.model.Key;
import com.gb.homework.repository.ClientRepository;
import com.gb.homework.repository.KeyRepository;
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

    @Autowired
    KeyRepository keyRepository;

    public UUID createClient(Client client) {
        Client newClient = Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .build();

        UUID apiKey = UUID.randomUUID();
        Key newKey = Key.builder()
                .apiKey(passWordEncoder.encode(apiKey.toString()))
                .client(newClient)
                .build();

        clientRepository.save(newClient);
        keyRepository.save(newKey);

        return apiKey;
    };
}
