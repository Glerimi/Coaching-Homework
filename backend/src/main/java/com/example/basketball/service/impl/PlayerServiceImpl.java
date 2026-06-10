package com.example.basketball.service.impl;

import com.example.basketball.model.PlayerEntity;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.repository.PlayerRepository;
import com.example.basketball.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<PlayerEntity> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public List<PlayerEntity> getPlayersByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAllPlayers();
        }
        return playerRepository.findByStatusIgnoreCase(status.trim());
    }

    @Override
    public PlayerEntity createPlayer(PlayerRequest request) {
        PlayerEntity player = new PlayerEntity(
                valueOrDefault(request.name(), "Unknown Player"),
                valueOrDefault(request.position(), "Unknown"),
                valueOrDefault(request.status(), "Active"),
                request.jerseyNumber() == null ? 0 : request.jerseyNumber()
        );
        return playerRepository.save(player);
    }

    @Override
    public Optional<PlayerEntity> updatePlayer(Long id, PlayerRequest request) {
        return playerRepository.findById(id).map(existingPlayer -> {
            existingPlayer.setName(valueOrDefault(request.name(), existingPlayer.getName()));
            existingPlayer.setPosition(valueOrDefault(request.position(), existingPlayer.getPosition()));
            existingPlayer.setStatus(valueOrDefault(request.status(), existingPlayer.getStatus()));
            existingPlayer.setJerseyNumber(request.jerseyNumber() == null ? existingPlayer.getJerseyNumber() : request.jerseyNumber());
            return playerRepository.save(existingPlayer);
        });
    }

    @Override
    public boolean deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            return false;
        }
        playerRepository.deleteById(id);
        return true;
    }

    private String valueOrDefault(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value.trim();
    }
}
