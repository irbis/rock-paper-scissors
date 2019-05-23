package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                MessageResolver.class,
                StartGameState.class,
                ExitGameState.class,
                SessionService.class })
public class WelcomeGameStateTest {

    @Autowired private MessageResolver messageResolver;
    @Autowired private StartGameState startGameState;
    @Autowired private ExitGameState exitGameState;

    private WelcomeGameState welcomeGameState;
    private SessionService sessionService;

    @Before
    public void setUp() {
        sessionService = new SessionService();
        welcomeGameState = new WelcomeGameState(
                messageResolver,
                startGameState,
                exitGameState,
                sessionService);
    }

    @Test
    public void actUsername() {
        GameState state = welcomeGameState.act("username");
        assertTrue(state instanceof StartGameState);
        assertEquals("username", sessionService.getCurrentSession());
    }

    @Test
    public void actEmptyString() {
        GameState state = welcomeGameState.act("");
        assertTrue(state instanceof ExitGameState);
        assertEquals("", sessionService.getCurrentSession());
    }

    @Test
    public void actSpacesAsUsername() {
        GameState state = welcomeGameState.act("   ");
        assertTrue(state instanceof ExitGameState);
        assertEquals("", sessionService.getCurrentSession());
    }

    @Test
    public void isContinue() {
        assertFalse(welcomeGameState.isContinue());
    }
}