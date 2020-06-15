package com.example.games.core;

public interface Board {

    public void setUp();
    public void move(Player player, int rowPosition, int colPosition);
}
