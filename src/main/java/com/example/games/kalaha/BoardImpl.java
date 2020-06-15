package com.example.games.kalaha;

import com.example.games.core.Player;
import com.example.games.shared.Tile;

public class BoardImpl implements com.example.games.core.Board
{
    private int id;

    private int pieceCount = 4;

    private int row = 2;
    private int col = 6;

    Tile[][] tiles;
    com.example.games.shared.Player[] players;

    public BoardImpl() {
        this.tiles = new Tile[row][col];
    }

    @Override
    public void setUp() {
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                tiles[r][c] = new Tile(players[r], pieceCount);
            }
        }
    }

    @Override
    public void move(Player player, int rowPosition, int colPosition) {

        int pieceCount = tiles[rowPosition][colPosition].getPieceCount();
        tiles[rowPosition][colPosition].setPieceCount(0);

        boolean isPlayerOne = rowPosition == 0;
        distribute(isPlayerOne, rowPosition, colPosition, pieceCount);
    }

    private void distribute(boolean isPlayerOne, int row, int col, int count){
        while(0 < count)
        {
            if(row == 0) --col;
            else ++col;

            if (row == 0 && col == -1){
                if(isPlayerOne)
                {
                    players[0].setStack(players[0].getStack() + 1);
                    --count;
                    if(count == 0) {
                        return;
                    }
                }
                row = 1;
                continue;
            }

            if (row == 1 && col == 6){
                if(!isPlayerOne){
                    players[1].setStack(players[1].getStack() + 1);
                    --count;
                    if(count == 0) {
                        return;
                    }
                }
                row = 0;
                continue;
            }

            if (count == 1 && tiles[row][col].getPieceCount() == 0) {
                if(isPlayerOne && row == 0){
                    var stack = players[0].getStack() + 1 + tiles[0][col].getPieceCount() + tiles[1][col].getPieceCount();
                    players[0].setStack(stack);
                    tiles[0][col].setPieceCount(0);
                    tiles[1][col].setPieceCount(0);
                    continue;
                }
                if(!isPlayerOne && row == 1){
                    var stack = players[1].getStack() + 1 + tiles[0][col].getPieceCount() + tiles[1][col].getPieceCount();
                    players[1].setStack(stack);
                    tiles[0][col].setPieceCount(0);
                    tiles[1][col].setPieceCount(0);
                    continue;
                }
            }

            tiles[row][col].setPieceCount(tiles[row][col].getPieceCount() + 1);
            --count;
        }

        switchPlayersTurn(isPlayerOne);
    }

    private void switchPlayersTurn(boolean isPlayerOne) {
        players[0].setPlayerTurn(!isPlayerOne);
        players[1].setPlayerTurn(isPlayerOne);
    }

//    private void distribute(boolean isPlayerOne, int row, int col, int count){
//
//        if (count == 0) return;
//
//        if (count == 1 && tiles[row][col].getPieceCount() == 0) {
//            tiles[row][col].setPieceCount(tiles[row][col].getPieceCount() + 1);
//
//            if(isPlayerOne && row == 0){
//                var stack = players[0].getStack() + tiles[0][col].getPieceCount() + tiles[1][col].getPieceCount();
//                players[0].setStack(stack);
//                return;
//            }
//            if(!isPlayerOne && row == 1){
//                var stack = players[1].getStack() + tiles[0][col].getPieceCount() + tiles[1][col].getPieceCount();
//                players[1].setStack(stack);
//                return;
//            }
//        }
//
//        if (row == 0 && col == 0){
//            if(isPlayerOne)
//            {
//                players[0].setStack(players[0].getStack() + 1);
//                --count;
//                if(count == 0) {
////                    set turn
//                    return;
//                }
//            }
//            row = 1;
//            col = -1;
//        }
//
//        if (row == 1 && col == 5){
//            if(!isPlayerOne){
//                players[1].setStack(players[1].getStack() + 1);
//                --count;
//                if(count == 0) {
////                    set turn
//                    return;
//                }
//            }
//            row = 0;
//            col = 6;
//        }
//
//        if(row == 0)
//            --col;
//        else
//            ++col;
//
//        tiles[row][col].setPieceCount(tiles[row][col].getPieceCount() + 1);
//
//        distribute(isPlayerOne, row, col, --count);
//    }

    public boolean canMove(Player player, int rowPosition, int colPosition) {
        if (tiles[rowPosition][colPosition].getPlayer() != player ||
            tiles[rowPosition][colPosition].getPieceCount() == 0)
            return false;

        return true;
    }

    public com.example.games.shared.Player getPlayer(String name) {
        for(int i=0; i<players.length; i++) {
            if (players[i].getName().equals(name)) {
                return players[i];
            }
        }
        return null;
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

    public int getCol() {
        return col;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public com.example.games.shared.Player[] getPlayers() {
        return players;
    }

    public void setPlayers(com.example.games.shared.Player[] players) {
        this.players = players;
    }
}

