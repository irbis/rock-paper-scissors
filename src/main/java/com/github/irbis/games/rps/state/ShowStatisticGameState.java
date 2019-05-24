package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.domain.AggregatedStatistic;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.StatisticService;
import org.springframework.stereotype.Component;

@Component
public class ShowStatisticGameState extends GameState {

    private final StatisticService statisticService;
    private final StartGameState startGameState;

    public ShowStatisticGameState(
            MessageResolver messageResolver,
            StatisticService statisticService,
            StartGameState startGameState) {
        super(messageResolver);
        this.statisticService = statisticService;
        this.startGameState = startGameState;
    }

    @Override
    public void show() {
        printlnMessage("gaming-statistic");
        printlnMessage("gaming-statistic-title");

        statisticService.getSortedAggregatedStatistic()
                .forEach(this::printStatisticLine);

        printlnMessage("enter-to-return");
    }

    private void printStatisticLine(AggregatedStatistic s) {
        println("| " + s.getUsername() + "\t\t| " + s.getScore() + "\t|");
    }

    @Override
    public GameState act(String input) {
        return startGameState;
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}