package com.example.basketball.service.impl;

import com.example.basketball.model.DashboardStats;
import com.example.basketball.model.DummyPlayer;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.model.Team;
import com.example.basketball.service.DummyBasketballService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DummyBasketballServiceImpl implements DummyBasketballService {
    private final List<DummyPlayer> players = new ArrayList<>(List.of(
            new DummyPlayer(1L, "Marcus Johnson", "Point Guard", "Active"),
            new DummyPlayer(2L, "Tyler Williams", "Shooting Guard", "Active"),
            new DummyPlayer(3L, "James Anderson", "Small Forward", "Injured"),
            new DummyPlayer(4L, "David Miller", "Power Forward", "Active"),
            new DummyPlayer(5L, "Chris Davis", "Center", "Active")
    ));

    private final List<Team> teams = List.of(
            new Team(1L, "Sarajevo Eagles", "Sarajevo", 18, 6),
            new Team(2L, "Mostar Lions", "Mostar", 15, 9),
            new Team(3L, "Tuzla Wolves", "Tuzla", 12, 12)
    );

    @Override
    public DashboardStats getStats() {
        return new DashboardStats(24, 87, "2 days");
    }

    @Override
    public List<DummyPlayer> getPlayers() {
        return players;
    }

    @Override
    public Optional<DummyPlayer> getPlayerById(Long id) {
        return players.stream().filter(player -> player.id().equals(id)).findFirst();
    }

    @Override
    public DummyPlayer createPlayer(PlayerRequest request) {
        long nextId = players.stream().mapToLong(DummyPlayer::id).max().orElse(0L) + 1;
        DummyPlayer player = new DummyPlayer(
                nextId,
                valueOrDefault(request.name(), "Unknown Player"),
                valueOrDefault(request.position(), "Unknown"),
                valueOrDefault(request.status(), "Active")
        );
        players.add(player);
        return player;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public Optional<Team> getTeamById(Long id) {
        return teams.stream().filter(team -> team.id().equals(id)).findFirst();
    }

    private String valueOrDefault(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value.trim();
    }
}
