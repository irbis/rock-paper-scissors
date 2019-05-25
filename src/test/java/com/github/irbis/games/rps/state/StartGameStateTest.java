package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.TestConfiguration;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class StartGameStateTest {

    private @Autowired MessageResolver messageResolver;
    private @Autowired PlayGameState playGameState;
    private @Autowired ExitGameState exitGameState;
    private @Autowired ShowStatisticGameState showStatisticGameState;
    private @Autowired GameState incorrectCommandGameState;
    private @Autowired SessionService sessionService;

    private StartGameState startGameState;

    @Before
    public void setUp() {
        startGameState = new StartGameState(
                messageResolver,
                playGameState,
                exitGameState,
                showStatisticGameState,
                incorrectCommandGameState,
                sessionService);

    }

    @Test
    public void act1() {
        GameState state = startGameState.act("1");

        assertTrue(state instanceof PlayGameState);
    }

    @Test
    public void actR() {
        GameState state = startGameState.act("r");

        assertTrue(state instanceof PlayGameState);
    }

    @Test
    public void act2() {
        GameState state = startGameState.act("2");

        assertTrue(state instanceof ShowStatisticGameState);
    }

    @Test
    public void actJ() {
        GameState state = startGameState.act("j");

        assertTrue(state instanceof ShowStatisticGameState);
    }

    @Test
    public void act0() {
        GameState state = startGameState.act("0");

        assertTrue(state instanceof ExitGameState);
    }

    @Test
    public void actQ() {
        GameState state = startGameState.act("q");

        assertTrue(state instanceof ExitGameState);
    }

    @Test
    public void actWrongCommand() {
        GameState state = startGameState.act("a");

        assertTrue(state instanceof IncorrectCommandGameState);
    }

    @Test
    public void isContinue() {
        assertTrue(startGameState.isContinue());
    }
}