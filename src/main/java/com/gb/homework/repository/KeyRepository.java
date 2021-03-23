package com.gb.homework.repository;

import com.gb.homework.model.Client;
import com.gb.homework.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, UUID> {

    @Override
    Optional<Key> findById(UUID id);
}
