package com.github.irbis.games.rps.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Statistic {
    private String username;
    private int successCount;
    private int failCount;

    public Statistic incSuccess() {
        return new Statistic(this.username, this.successCount + 1, this.failCount);
    }

    public Statistic incFail() {
        return new Statistic(this.username, this.successCount, this.failCount + 1);
    }
}
