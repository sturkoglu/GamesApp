package com.example.games.backgammon;

import com.example.games.core.Player;
import com.example.games.shared.Tile;

public class Board implements com.example.games.core.Board
{
    private int id;
    private int row = 2;
    private int col = 6;
    private int pieceCount = 4;

    Tile[][] holderTable;
    com.example.games.shared.Player[] players;

    public Board(com.example.games.shared.Player[] players) {
        this.holderTable = new Tile[row][col];
        this.players = players;
    }

    @Override
    public void setUp() {
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                holderTable[r][c] = new Tile(players[r], pieceCount);
            }
        }
    }

    private void distribute(boolean isPlayerOne, int row, int col, int count){

    }

    @Override
    public void move(Player player, int rowPosition, int colPosition) {
        boolean isPlayerOne = rowPosition == 0;

        int pieceCount = holderTable[rowPosition][colPosition].getPieceCount();
        holderTable[rowPosition][colPosition].setPieceCount(0);

        distribute(isPlayerOne, rowPosition, colPosition, pieceCount);
    }

    public boolean canMove(Player player, int rowPosition, int colPosition) {
        if (holderTable[rowPosition][colPosition].getPlayer() != player ||
                holderTable[rowPosition][colPosition].getPieceCount() == 0)
            return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public Tile[][] getHolderTable() {
        return holderTable;
    }

    public void setHolderTable(Tile[][] holderTable) {
        this.holderTable = holderTable;
    }

    public com.example.games.shared.Player[] getPlayers() {
        return players;
    }

    public void setPlayers(com.example.games.shared.Player[] players) {
        this.players = players;
    }
}
