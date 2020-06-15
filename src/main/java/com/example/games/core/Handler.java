package com.example.games.core;

//todo: Remove I and change extended classes with Iplm
public interface Handler<Tin extends Command, Tout extends Result> {

    Tout handle(Tin command);
}
