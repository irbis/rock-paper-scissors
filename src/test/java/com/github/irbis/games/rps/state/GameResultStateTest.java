package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.TestConfiguration;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class GameResultStateTest {

    @Autowired private MessageResolver messageResolver;
    @Autowired private StartGameState startGameState;
    @Autowired private SessionService sessionService;
    @Autowired private StatisticService statisticService;

    @Test
    public void actWin() {
        GameResultState gameResultState = createGameResultState(GameResult.WIN);
        GameState state = gameResultState.act("");

        assertTrue(state instanceof StartGameState);
    }

    @Test
    public void actLoose() {
        GameResultState gameResultState = createGameResultState(GameResult.LOOSE);
        GameState state = gameResultState.act("");

        assertTrue(state instanceof StartGameState);
    }

    @Test
    public void actDraw() {
        GameResultState gameResultState = createGameResultState(GameResult.DRAW);
        GameState state = gameResultState.act("");

        assertTrue(state instanceof StartGameState);
    }

    private GameResultState createGameResultState(GameResult gameResult) {
        return new GameResultState(
                messageResolver,
                gameResult,
                startGameState,
                sessionService,
                statisticService);
    }
}