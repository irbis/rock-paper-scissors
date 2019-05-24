package com.github.irbis.games.rps.state;

import com.github.irbis.games.rps.repository.InMemoryStatisticRepository;
import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.service.SessionService;
import com.github.irbis.games.rps.service.StatisticService;
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
                ShowStatisticGameState.class,
                SessionService.class,
                StatisticService.class,
                InMemoryStatisticRepository.class})
public class ShowStatisticGameStateTest {

    @Autowired private MessageResolver messageResolver;
    @Autowired private StatisticService statisticService;
    @Autowired private StartGameState startGameState;

    private ShowStatisticGameState showStatisticGameState;

    @Before
    public void setUp() {
        showStatisticGameState = new ShowStatisticGameState(
                messageResolver,
                startGameState,
                statisticService);
    }

    @Test
    public void act() {
        GameState state = showStatisticGameState.act("username");

        assertTrue(state instanceof StartGameState);
    }

    @Test
    public void isContinue() {
        assertTrue(showStatisticGameState.isContinue());
    }
}