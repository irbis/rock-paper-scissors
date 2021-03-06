package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class RockPlayGameStateTest extends PlayGameStateTest {

    @Test
    public void actRockWin() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_SCISSORS);

        GameState state = playGameState.act(ROCK_KEY);

        assertTrue(state instanceof GameResultState);
        assertEquals(GameResult.WIN, ((GameResultState) state).getGameResult());
    }

    @Test
    public void actRockLoose() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_PAPER);

        GameState state = playGameState.act(ROCK_KEY);

        assertTrue(state instanceof GameResultState);
        assertEquals(GameResult.LOOSE, ((GameResultState) state).getGameResult());
    }

    @Test
    public void actRockDraw() {
        PlayGameState playGameState = createPlayGameState(COMPUTER_TURN_ROCK);

        GameState state = playGameState.act(ROCK_KEY);

        assertTrue(state instanceof GameResultState);
        assertEquals(GameResult.DRAW, ((GameResultState) state).getGameResult());
    }

}