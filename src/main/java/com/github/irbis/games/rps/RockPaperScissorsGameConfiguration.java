package com.github.irbis.games.rps;

import com.github.irbis.games.rps.service.MessageResolver;
import com.github.irbis.games.rps.state.GameState;
import com.github.irbis.games.rps.state.NotificationGameState;
import com.github.irbis.games.rps.state.StartGameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RockPaperScissorsGameConfiguration {

    @Autowired MessageResolver messageResolver;
    @Autowired StartGameState startGameState;

    @Bean(name = "incorrectCommandGameState")
    public GameState incorrectCommandGameState() {
        return new NotificationGameState(messageResolver, "incorrect-command", startGameState);
    }

}
