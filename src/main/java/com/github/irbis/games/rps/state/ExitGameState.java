package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import org.springframework.stereotype.Component;

@Component
public class ExitGameState extends GameState {

    public ExitGameState(MessageResolver messageResolver) {
        super(messageResolver);
    }

    @Override
    public void show() {
        printlnMessage("exit");
    }

    @Override
    public GameState act(String input) {
        return null;
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}
