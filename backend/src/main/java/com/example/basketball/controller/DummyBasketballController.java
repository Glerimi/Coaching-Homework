package com.example.basketball.controller;

import com.example.basketball.model.DashboardStats;
import com.example.basketball.model.DummyPlayer;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.model.Team;
import com.example.basketball.service.DummyBasketballService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DummyBasketballController {
    private final DummyBasketballService dummyBasketballService;

    public DummyBasketballController(DummyBasketballService dummyBasketballService) {
        this.dummyBasketballService = dummyBasketballService;
    }

    @GetMapping("/stats")
    public DashboardStats getStats() {
        return dummyBasketballService.getStats();
    }

    @GetMapping("/players")
    public List<DummyPlayer> getPlayers() {
        return dummyBasketballService.getPlayers();
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<DummyPlayer> getPlayerById(@PathVariable Long id) {
        return dummyBasketballService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/players")
    public DummyPlayer createPlayer(@RequestBody PlayerRequest request) {
        return dummyBasketballService.createPlayer(request);
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return dummyBasketballService.getTeams();
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return dummyBasketballService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
