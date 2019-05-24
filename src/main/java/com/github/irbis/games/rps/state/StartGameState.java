package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StartGameState extends GameState {

    private enum Command {
        START,
        DISPLAY_STATISTIC,
        EXIT,
        UKNOWN
    }

    private final ExitGameState exitGameState;
    private final ShowStatisticGameState showStatisticGameState;
    private final GameState incorrectCommandGameState;
    private final SessionService sessionService;

    public StartGameState(
            MessageResolver messageResolver,
            ExitGameState exitGameState,
            ShowStatisticGameState showStatisticGameState,
            @Lazy GameState incorrectCommandGameState,
            SessionService sessionService) {
        super(messageResolver);
        this.exitGameState = exitGameState;
        this.showStatisticGameState = showStatisticGameState;
        this.incorrectCommandGameState = incorrectCommandGameState;
        this.sessionService = sessionService;
    }

    @Override
    public void show() {
        printlnMessage("select-action", sessionService.getCurrentSession());
        printlnMessage("start-new-game");
        printlnMessage("display-statistic");
        printlnMessage("exit-action-state");
    }

    @Override
    public GameState act(String input) {
        switch (parseInput(input)) {
            case START:
                return null; // TODO
            case DISPLAY_STATISTIC:
                return showStatisticGameState;
            case EXIT:
                return exitGameState;
        }

        return incorrectCommandGameState;
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    private Command parseInput(String input) {
        if (input.equals("1") || input.equalsIgnoreCase("r"))
            return Command.START;
        else if (input.equals("2") || input.equalsIgnoreCase("j"))
            return Command.DISPLAY_STATISTIC;
        else if (input.equals("0") || input.equalsIgnoreCase("q"))
            return Command.EXIT;


        return Command.UKNOWN;
    }
}
