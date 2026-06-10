package com.example.basketball;

import com.example.basketball.model.PlayerEntity;
import com.example.basketball.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PlayerRepository playerRepository;

    public DataSeeder(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() > 0) {
            return;
        }

        playerRepository.saveAll(List.of(
                new PlayerEntity("Marcus Johnson", "Point Guard", "Active", 7),
                new PlayerEntity("Tyler Williams", "Shooting Guard", "Active", 11),
                new PlayerEntity("James Anderson", "Small Forward", "Injured", 23),
                new PlayerEntity("David Miller", "Power Forward", "Active", 34),
                new PlayerEntity("Chris Davis", "Center", "Active", 55)
        ));
    }
}
