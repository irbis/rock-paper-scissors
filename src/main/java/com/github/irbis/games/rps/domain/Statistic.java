package com.github.irbis.games.rps.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Statistic {
    private String username;
    private int successCount;
    private int failCount;

}
