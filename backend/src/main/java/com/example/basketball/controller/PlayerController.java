package com.example.basketball.controller;

import com.example.basketball.model.PlayerEntity;
import com.example.basketball.model.PlayerRequest;
import com.example.basketball.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/db/players")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://DEINE-NETLIFY-SEITE.netlify.app"
})
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerEntity> getPlayers(@RequestParam(required = false) String status) {
        return status == null ? playerService.getAllPlayers() : playerService.getPlayersByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerEntity> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerEntity> createPlayer(@RequestBody PlayerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerEntity> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequest request) {
        return playerService.updatePlayer(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        return playerService.deletePlayer(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
