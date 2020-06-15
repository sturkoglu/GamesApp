package com.example.games.kalaha.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StartRequest {
    private PlayerRequest player1;
    private PlayerRequest player2;

    @JsonCreator
    public StartRequest(@JsonProperty("player1") PlayerRequest player1,
                        @JsonProperty("player2") PlayerRequest player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerRequest getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerRequest player1) {
        this.player1 = player1;
    }

    public PlayerRequest getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerRequest player2) {
        this.player2 = player2;
    }
}

