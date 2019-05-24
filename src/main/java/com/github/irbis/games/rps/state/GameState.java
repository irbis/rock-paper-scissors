package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;

public abstract class GameState {
    protected final MessageResolver messageResolver;

    public GameState(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    protected void printlnMessage(String key) {
        System.out.println(messageResolver.getMessage(key));
    }

    protected void printlnMessage(String key, String... value) {
        System.out.printf(messageResolver.getMessage(key), value);
    }

    protected void printMessage(String key) {
        System.out.print(messageResolver.getMessage(key));
    }

    public abstract void show();
    public abstract GameState act(String input);
    public abstract boolean isContinue();
}
