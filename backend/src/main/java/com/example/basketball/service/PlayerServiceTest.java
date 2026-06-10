package com.example.basketball.service;

import com.example.basketball.model.PlayerEntity;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private PlayerEntity player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        player = new PlayerEntity();
        player.setId(1L);
        player.setName("Marcus Johnson");
        player.setPosition("Point Guard");
        player.setStatus("Active");
        player.setJerseyNumber(7);
    }

    @Test
    void getAllPlayersReturnsPlayers() {
        when(playerRepository.findAll()).thenReturn(List.of(player));

        List<PlayerEntity> result = playerService.getAllPlayers();

        assertEquals(1, result.size());
        assertEquals("Marcus Johnson", result.get(0).getName());
        verify(playerRepository).findAll();
    }

    @Test
    void getPlayersByStatusReturnsFilteredPlayers() {
        when(playerRepository.findByStatusIgnoreCase("Active")).thenReturn(List.of(player));

        List<PlayerEntity> result = playerService.getPlayersByStatus("Active");

        assertEquals(1, result.size());
        assertEquals("Active", result.get(0).getStatus());
        verify(playerRepository).findByStatusIgnoreCase("Active");
    }

    @Test
    void getPlayerByIdReturnsPlayerWhenExists() {
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Optional<PlayerEntity> result = playerService.getPlayerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Marcus Johnson", result.get().getName());
        verify(playerRepository).findById(1L);
    }

    @Test
    void getPlayerByIdReturnsEmptyWhenNotExists() {
        when(playerRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PlayerEntity> result = playerService.getPlayerById(99L);

        assertTrue(result.isEmpty());
        verify(playerRepository).findById(99L);
    }

    @Test
    void createPlayerSavesAndReturnsPlayer() {
        PlayerRequest request = new PlayerRequest(
                "Tyler Williams",
                "Shooting Guard",
                "Active",
                11
        );

        when(playerRepository.save(any(PlayerEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerEntity result = playerService.createPlayer(request);

        assertEquals("Tyler Williams", result.getName());
        assertEquals("Shooting Guard", result.getPosition());
        assertEquals("Active", result.getStatus());
        assertEquals(11, result.getJerseyNumber());
        verify(playerRepository).save(any(PlayerEntity.class));
    }

    @Test
    void updatePlayerUpdatesWhenPlayerExists() {
        PlayerRequest request = new PlayerRequest(
                "Updated Player",
                "Center",
                "Injured",
                22
        );

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(playerRepository.save(any(PlayerEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<PlayerEntity> result = playerService.updatePlayer(1L, request);

        assertTrue(result.isPresent());
        assertEquals("Updated Player", result.get().getName());
        assertEquals("Center", result.get().getPosition());
        assertEquals("Injured", result.get().getStatus());
        assertEquals(22, result.get().getJerseyNumber());

        verify(playerRepository).findById(1L);
        verify(playerRepository).save(player);
    }

    @Test
    void updatePlayerReturnsEmptyWhenPlayerDoesNotExist() {
        PlayerRequest request = new PlayerRequest(
                "Nobody",
                "Guard",
                "Active",
                5
        );

        when(playerRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PlayerEntity> result = playerService.updatePlayer(99L, request);

        assertTrue(result.isEmpty());
        verify(playerRepository).findById(99L);
        verify(playerRepository, never()).save(any());
    }

    @Test
    void deletePlayerReturnsTrueWhenExists() {
        when(playerRepository.existsById(1L)).thenReturn(true);

        boolean result = playerService.deletePlayer(1L);

        assertTrue(result);
        verify(playerRepository).existsById(1L);
        verify(playerRepository).deleteById(1L);
    }

    @Test
    void deletePlayerReturnsFalseWhenNotExists() {
        when(playerRepository.existsById(99L)).thenReturn(false);

        boolean result = playerService.deletePlayer(99L);

        assertFalse(result);
        verify(playerRepository).existsById(99L);
        verify(playerRepository, never()).deleteById(anyLong());
    }
}