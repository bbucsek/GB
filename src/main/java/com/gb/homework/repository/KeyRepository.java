package com.gb.homework.repository;

import com.gb.homework.model.Client;
import com.gb.homework.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, String> {
}
