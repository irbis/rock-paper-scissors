package com.github.irbis.games.rps.service;

import com.github.irbis.games.rps.domain.AggregatedStatistic;
import com.github.irbis.games.rps.domain.Statistic;
import com.github.irbis.games.rps.repository.StatisticRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class StatisticService {
    private final StatisticRepository statisticRepository;


    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }


    public List<AggregatedStatistic> getSortedAggregatedStatistic() {
        return statisticRepository.findAll().stream()
                .map(this::createAggregatedStatistic)
                .sorted(new Comparator<AggregatedStatistic>() {
            @Override
            public int compare(AggregatedStatistic o1, AggregatedStatistic o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        }).collect(toList());
    }

    private AggregatedStatistic createAggregatedStatistic(Statistic s) {
        int score = 100 * s.getSuccessCount() / (s.getSuccessCount() + s.getFailCount());

        return new AggregatedStatistic(s.getUsername(), score);
    }
}
