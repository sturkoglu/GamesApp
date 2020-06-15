package com.example.games.kalaha.viewModel;

import com.example.games.kalaha.BoardImpl;
import com.example.games.shared.Tile;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BoardViewModel
{
    private int id;
    private TileViewModel[][] tiles;
    private PlayerViewModel[] players;

    @JsonCreator
    public BoardViewModel(BoardImpl board) {
        this.id = board.getId();

        tiles = new TileViewModel[board.getRow()][board.getCol()];
        for (int r = 0; r < board.getRow(); ++r) {
            for (int c = 0; c < board.getCol(); ++c) {
                Tile[][] tempTiles = board.getTiles();
                tiles[r][c] = new TileViewModel(tempTiles[r][c].getPlayer().getName(), tempTiles[r][c].getPieceCount());
            }
        }

        com.example.games.shared.Player[] boardPlayers = board.getPlayers();
        players = new PlayerViewModel[boardPlayers.length];
        for (int r = 0; r < boardPlayers.length; ++r) {
            this.players[r] = new PlayerViewModel(boardPlayers[r].getName(), boardPlayers[r].getStack(), boardPlayers[r].isPlayerTurn());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TileViewModel[][] getTiles() {
        return tiles;
    }

    public void setTiles(TileViewModel[][] tiles) {
        this.tiles = tiles;
    }

    public PlayerViewModel[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerViewModel[] players) {
        this.players = players;
    }
}

