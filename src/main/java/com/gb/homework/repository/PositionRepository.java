package com.gb.homework.repository;

import com.gb.homework.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {

    Set<Position> findAllByLocation(String location);

    @Query(
            "SELECT p FROM Position p " +
            "WHERE p.title LIKE %?1% " +
            "AND p.location LIKE %?2% "
    )
    Set<Position> getPositionByKeywordAndLocation(String title, String location);
}
