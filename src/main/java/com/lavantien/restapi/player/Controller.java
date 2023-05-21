package com.lavantien.restapi.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public Controller(Repository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return repository.findAll();
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return repository.save(player);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return repository.findById(id).map(e -> {
            e.setName(player.getName());
            e.setEmail(player.getEmail());
            e.setPassword(player.getPassword());
            e.setDateOfBirth(player.getDateOfBirth());
            return repository.save(e);
        }).orElseGet(() -> {
            player.setId(id);
            return repository.save(player);
        });
    }

    @PatchMapping
    public List<Player> patchPlayers(@RequestBody List<Player> players) {
        return repository.saveAll(players);
    }
}
