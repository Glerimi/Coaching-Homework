package com.example.basketball.service;

import com.example.basketball.model.PlayerEntity;
import com.example.basketball.model.PlayerRequest;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<PlayerEntity> getAllPlayers();
    Optional<PlayerEntity> getPlayerById(Long id);
    List<PlayerEntity> getPlayersByStatus(String status);
    PlayerEntity createPlayer(PlayerRequest request);
    Optional<PlayerEntity> updatePlayer(Long id, PlayerRequest request);
    boolean deletePlayer(Long id);
}
