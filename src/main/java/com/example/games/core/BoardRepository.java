package com.example.games.core;

public interface BoardRepository<T extends Board> {
    T getById(int boardId);
    T save(T board);
    T update(T board);
}
