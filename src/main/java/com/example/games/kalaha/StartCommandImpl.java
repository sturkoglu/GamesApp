package com.example.games.kalaha;

import com.example.games.core.Command;

public class StartCommandImpl implements Command {

    private String player1;
    private String player2;

    public StartCommandImpl(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
