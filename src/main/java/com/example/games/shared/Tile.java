package com.example.games.shared;

public class Tile {
    Player player;
    int pieceCount;

    public Tile(Player player, int count){
        this.player = player;
        this.pieceCount = count;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }
}
