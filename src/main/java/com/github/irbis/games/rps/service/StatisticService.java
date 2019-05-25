package com.github.irbis.games.rps.service;

import com.github.irbis.games.rps.domain.AggregatedStatistic;
import com.github.irbis.games.rps.domain.Statistic;
import com.github.irbis.games.rps.repository.StatisticRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void initStatistic(String username) {
        Optional<Statistic> s = statisticRepository.find(username);

        if (!s.isPresent()) {
            statisticRepository.saveOrUpdate(
                Statistic.builder().username(username).build());
        }
    }

    public List<AggregatedStatistic> getSortedAggregatedStatistic() {
        return statisticRepository.findAll().stream()
                .map(this::createAggregatedStatistic)
                .sorted((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()))
                .collect(toList());
    }

    private AggregatedStatistic createAggregatedStatistic(Statistic s) {
        int score = 0;

        if (s.getSuccessCount() != 0 || s.getFailCount() != 0) {
            score = 100 * s.getSuccessCount() / (s.getSuccessCount() + s.getFailCount());
        }

        return new AggregatedStatistic(s.getUsername(), score);
    }

    public void win(String username) {
        Optional<Statistic> s = statisticRepository.find(username);

        if (s.isPresent()) {
            statisticRepository.saveOrUpdate(s.get().incSuccess());
        } else {
            statisticRepository.saveOrUpdate(
                    Statistic.builder()
                            .username(username)
                            .successCount(1)
                            .build());
        }
    }

    public void fail(String username) {
        Optional<Statistic> s = statisticRepository.find(username);

        if (s.isPresent()) {
            statisticRepository.saveOrUpdate(s.get().incFail());
        } else {
            statisticRepository.saveOrUpdate(
                    Statistic.builder()
                            .username(username)
                            .failCount(1)
                            .build());
        }
    }
}
