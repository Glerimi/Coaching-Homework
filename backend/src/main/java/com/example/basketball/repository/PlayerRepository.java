package com.example.basketball.repository;

import com.example.basketball.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerEntity> findByStatusIgnoreCase(String status);
}
