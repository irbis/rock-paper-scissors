package com.github.irbis.games.rps.repository;

import com.github.irbis.games.rps.domain.Statistic;

import java.util.Optional;

public interface StatisticRepository {
    Optional<Statistic> find(String username);
    void saveOrUpdate(Statistic statistic);
}
