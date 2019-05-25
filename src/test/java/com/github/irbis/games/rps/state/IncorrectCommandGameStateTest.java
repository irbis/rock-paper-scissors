package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.TestConfiguration;
import com.github.irbis.games.rps.service.MessageResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class IncorrectCommandGameStateTest {

    @Autowired private MessageResolver messageResolver;
    @Autowired private StartGameState startGameState;

    private IncorrectCommandGameState incorrectCommandGameState;

    @Before
    public void setUp() {
        incorrectCommandGameState = new IncorrectCommandGameState(messageResolver, startGameState);
    }

    @Test
    public void act() {
        GameState state = incorrectCommandGameState.act("");
        assertTrue(state instanceof StartGameState);
    }

    @Test
    public void isContinue() {
        assertTrue(incorrectCommandGameState.isContinue());
    }
}