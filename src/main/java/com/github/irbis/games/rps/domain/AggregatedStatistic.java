package com.github.irbis.games.rps.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AggregatedStatistic {
    private final String username;
    private final int score;
}
