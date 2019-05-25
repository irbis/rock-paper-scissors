package com.github.irbis.games.rps;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.github.irbis.games.rps",
        excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= RockPaperScissorsGame.class)})
@EnableAutoConfiguration
public class TestConfiguration {
}
