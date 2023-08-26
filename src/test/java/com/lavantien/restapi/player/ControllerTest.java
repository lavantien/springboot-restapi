package com.lavantien.restapi.player;

// import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ControllerTest {
    private Repository repository;
    private Controller controller;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
        controller = new Controller(repository);
    }

    @Test
    void getPlayers() {
        var emptyList = new ArrayList<Player>();
        Mockito.when(repository.findAll()).thenReturn(emptyList);
        var foundPlayers = controller.getPlayers();
        Assertions.assertThat(foundPlayers.getStatusCode().toString()).isEqualTo("200 OK");
    }

    // private static List<Player> generatePlayers() {
    //     var players = new ArrayList<Player>();
    //     var alphabet = "abcde".toCharArray();
    //     for (var c : alphabet) {
    //         players.add(
    //                 new Player("player " + String.valueOf(c),
    //                         String.valueOf(c) + "@" + String.valueOf(c) + ".com",
    //                         "password", LocalDate.now()));
    //     }
    //     return players;
    // }
}
