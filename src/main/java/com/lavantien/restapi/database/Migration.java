package com.lavantien.restapi.database;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lavantien.restapi.player.Player;
import com.lavantien.restapi.player.Repository;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(Repository repository) {
        return args -> {
            var alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            for (var c : alphabet) {
                var player = repository.save(new Player("player " + String.valueOf(c), String.valueOf(c) + "@" + String.valueOf(c) + ".com", "password", LocalDate.now()));
                log.info("Migrating: " + player);
            }
        };
    }
}
