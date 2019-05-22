package com.github.irbis.games.rps.repository;

import com.github.irbis.games.rps.domain.Statistic;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.synchronizedMap;

@Repository
public class InMemoryStatisticRepository implements StatisticRepository {
    private static Logger LOG = LoggerFactory.getLogger(InMemoryStatisticRepository.class);

    private final Map<String, Statistic> statistics = synchronizedMap(new HashMap<>());

    @PostConstruct
    private void init() {
        initStatisticFromFile("statistic.csv");
    }

    private void initStatisticFromFile(String fileName) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
            Reader csvReader = new InputStreamReader(stream);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);

            for (CSVRecord statisticRecord : records) {
                Statistic statistic = createStatistic(statisticRecord);
                statistics.put(statistic.getUsername(), statistic);
            }
        } catch (NullPointerException | IOException e) {
            LOG.info("Unable to read statistic! Continue with empty table!");
        }
    }

    private Statistic createStatistic(CSVRecord csvRecord) {
        return Statistic.builder()
                .username(csvRecord.get("username"))
                .successCount(Integer.parseInt(csvRecord.get("successCount")))
                .failCount(Integer.parseInt(csvRecord.get("failCount")))
                .build();
    }

    @Override
    public Optional<Statistic> find(String username) {
        Statistic statistic = statistics.get(username);
        return Optional.ofNullable(statistic);
    }

    @Override
    public void saveOrUpdate(Statistic statistic) {
        statistics.put(statistic.getUsername(), statistic);
    }
}
