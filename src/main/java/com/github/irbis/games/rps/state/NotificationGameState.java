package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;

public class NotificationGameState extends GameState {
    private final String messageKey;
    private final GameState nextState;

    public NotificationGameState(MessageResolver messageResolver, GameState nextState) {
        super(messageResolver);
        messageKey = "";
        this.nextState = nextState;
    }

    public NotificationGameState(MessageResolver messageResolver, String messageKey,
                                 GameState nextState) {
        super(messageResolver);
        this.messageKey = messageKey;
        this.nextState = nextState;
    }

    @Override
    public void show() {
        if (!messageKey.isEmpty())
            printlnMessage(messageKey);
    }

    @Override
    public GameState act(String input) {
        return nextState;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
