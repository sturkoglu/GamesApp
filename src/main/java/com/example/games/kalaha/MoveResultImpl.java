package com.example.games.kalaha;

import com.example.games.core.Result;

import java.util.ArrayList;
import java.util.List;

public class MoveResultImpl implements Result<BoardImpl> {

    private BoardImpl content;
    private List<String> errorMessages = new ArrayList<String>();

    @Override
    public BoardImpl getContent() {
        return content;
    }

    public void setContent(BoardImpl content) {
        this.content = content;
    }

    @Override
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public void addErrorMessages(String message) {
        errorMessages.add(message);
    }

    @Override
    public boolean isSuccess() {
        return errorMessages.isEmpty();
    }
}