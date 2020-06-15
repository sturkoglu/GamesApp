package com.example.games.kalaha;

import com.example.games.core.Command;

public class GetBoardCommandImpl implements Command {
    private int BoardId;

    public GetBoardCommandImpl(int boardId) {
        this.BoardId = boardId;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public int getBoardId() {
        return BoardId;
    }
}
