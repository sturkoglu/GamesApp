package com.example.games.kalaha.viewModel;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonClassDescription("player")
class PlayerViewModel {

    private String name;
    private int stack;
    private boolean playerTurn;

    @JsonCreator
    public PlayerViewModel(String name, int stack, boolean playerTurn) {
        this.name = name;
        this.stack = stack;
        this.playerTurn = playerTurn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
