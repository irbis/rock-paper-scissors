package com.github.irbis.games.rps.repository;

import com.github.irbis.games.rps.domain.Statistic;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

@Repository
public class InMemoryStatisticRepository {
    private static Logger LOG = LoggerFactory.getLogger(InMemoryStatisticRepository.class);

    private final List<Statistic> statistics = synchronizedList(new ArrayList<>());

    public InMemoryStatisticRepository() {
        initStatisticFromFile("statistic.csv");
    }

    private void initStatisticFromFile(String fileName) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
            Reader csvReader = new InputStreamReader(stream);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);

            for (CSVRecord statisticRecord : records) {
                statistics.add(createStatistic(statisticRecord));
            }
        } catch (Exception e) {
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

}
