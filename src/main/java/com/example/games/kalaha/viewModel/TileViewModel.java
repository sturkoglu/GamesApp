package com.example.games.kalaha.viewModel;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;

@JsonClassDescription("tile")
class TileViewModel {
    private String playerName;
    private int pieceCount;

    @JsonCreator
    public TileViewModel(String playerName, int pieceCount) {
        this.playerName = playerName;
        this.pieceCount = pieceCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }
}
