package com.example.games.kalaha;

import com.example.games.core.Command;

public class MoveCommandImpl implements Command {
    private int BoardId;
    private String playerName;

    private int rowPosition;
    private int colPosition;

    public MoveCommandImpl(int boardId, String playerName, int rowPosition, int colPosition) {
        this.BoardId = boardId;
        this.playerName = playerName;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    @Override
    public boolean isValid() {

        if(rowPosition < 0 || 1 < rowPosition || colPosition < 0 || 5 < colPosition)
            return false;

        return true;
    }

    public int getBoardId() {
        return BoardId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }


}
