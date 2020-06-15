package com.example.games.core;

import java.util.List;

public interface Result<T> {
    T getContent();
    List<String> getErrorMessages();
    void addErrorMessages(String message);
    boolean isSuccess();
}

