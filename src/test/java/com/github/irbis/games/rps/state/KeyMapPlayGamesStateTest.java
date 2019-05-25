package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class KeyMapPlayGamesStateTest extends PlayGameStateTest {

    @Test
    public void actRock() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_ROCK);
        GameState state;

        state = playGameState.act("1");
        assertTrue(state instanceof GameResultState);

        state = playGameState.act("a");
        assertTrue(state instanceof GameResultState);
    }

    @Test
    public void actPaper() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_PAPER);
        GameState state;

        state = playGameState.act("2");
        assertTrue(state instanceof GameResultState);

        state = playGameState.act("s");
        assertTrue(state instanceof GameResultState);
    }

    @Test
    public void actScissors() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_SCISSORS);
        GameState state;

        state = playGameState.act("3");
        assertTrue(state instanceof GameResultState);

        state = playGameState.act("d");
        assertTrue(state instanceof GameResultState);
    }

    @Test
    public void actWrong() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_SCISSORS);
        GameState state;

        state = playGameState.act("4");
        assertTrue(state instanceof IncorrectCommandGameState);

        state = playGameState.act("f");
        assertTrue(state instanceof IncorrectCommandGameState);
    }

}
