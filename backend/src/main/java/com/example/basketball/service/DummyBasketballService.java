package com.example.basketball.service;

import com.example.basketball.model.DashboardStats;
import com.example.basketball.model.DummyPlayer;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.model.Team;

import java.util.List;
import java.util.Optional;

public interface DummyBasketballService {
    DashboardStats getStats();
    List<DummyPlayer> getPlayers();
    Optional<DummyPlayer> getPlayerById(Long id);
    DummyPlayer createPlayer(PlayerRequest request);
    List<Team> getTeams();
    Optional<Team> getTeamById(Long id);
}
