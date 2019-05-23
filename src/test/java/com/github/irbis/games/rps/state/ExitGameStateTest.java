package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.service.MessageResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageResolver.class)
public class ExitGameStateTest {

    @Autowired private MessageResolver messageResolver;

    private ExitGameState exitGameState;

    @Before
    public void setUp() {
        exitGameState = new ExitGameState(messageResolver);
    }

    @Test
    public void isContinue() {
        assertFalse(exitGameState.isContinue());
    }
}