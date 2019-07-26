package com.github.irbis.games.rps.service;

import com.github.irbis.games.rps.state.Turn;

public interface ComputerPlayerService {
    Turn turn();

    void saveGamerTurn(Turn gamerTurn);
}
