package com.lavantien.restapi.player;

import com.lavantien.restapi.player.Validator.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        var foundPlayers = repository.findAll();
        return ResponseEntity.status(200).body(foundPlayers);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        var validationResult = Validator.validEmail().and(Validator.validAge()).apply(player);
        if (validationResult != ValidationResult.SUCCESS) {
            return ResponseEntity.status(400).build();
        }
        var savedPlayer = repository.save(player);
        return ResponseEntity.status(201).body(savedPlayer);
    }

    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        var foundPlayer = repository.findById(id).orElse(null);
        if (foundPlayer == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(foundPlayer);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        var foundPlayer = repository.findById(id).orElse(null);
        if (foundPlayer == null) {
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id,
            @RequestBody Player player) {
        var foundPlayer = repository.findById(id).orElse(null);
        if (foundPlayer == null) {
            player.setId(id);
            return ResponseEntity.status(201).body(repository.save(player));
        }
        var validationResult = Validator.validEmail().and(Validator.validAge()).apply(player);
        if (validationResult != ValidationResult.SUCCESS) {
            return ResponseEntity.status(400).build();
        }
        foundPlayer.setName(player.getName());
        foundPlayer.setEmail(player.getEmail());
        foundPlayer.setPassword(player.getPassword());
        foundPlayer.setDateOfBirth(player.getDateOfBirth());
        return ResponseEntity.status(202).body(repository.save(foundPlayer));
    }

    @PatchMapping
    public ResponseEntity<List<Player>> patchPlayers(@RequestBody List<Player> players) {
        var errorPlayers = new ArrayList<Player>();
        var okPlayers = new ArrayList<Player>();
        for (var player : players) {
            var validationResult = Validator.validEmail().and(Validator.validAge()).apply(player);
            if (validationResult != ValidationResult.SUCCESS) {
                errorPlayers.add(player);
            } else {
                okPlayers.add(player);
            }
        }
        if (errorPlayers.size() > 0) {
            return ResponseEntity.status(400).body(errorPlayers);
        }
        var savedPlayers = repository.saveAll(okPlayers);
        return ResponseEntity.status(201).body(savedPlayers);
    }
}
