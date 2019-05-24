package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.domain.AggregatedStatistic;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.StatisticService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ShowStatisticGameState extends GameState {

    private final StartGameState startGameState;
    private final StatisticService statisticService;

    public ShowStatisticGameState(
            MessageResolver messageResolver,
            @Lazy StartGameState startGameState,
            StatisticService statisticService) {
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

        printlnMessage("enter-to-continue");
    }

    private void printStatisticLine(AggregatedStatistic s) {
        printlnMessage("gaming-statistic-line",
                s.getUsername(), Integer.toString(s.getScore()) + "%");
    }

    @Override
    public GameState act(String input) {
        return startGameState;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
