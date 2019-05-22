package com.github.irbis.games.rps.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Service
public final class MessageResolver {
    private static final Map<String, String> MESSAGES_MAP =
            Stream.of(new String[][] {
                    { "welcome", "Welcome to 'Rock Paper Scissors' game!" },
                    { "login", "Please enter your username or press <Enter> to quit:" }
            }).collect(Collectors.collectingAndThen(
                    toMap(data -> data[0], data -> data[1]),
                    Collections::<String, String> unmodifiableMap));

    public String getMessage(String key) {
        return MESSAGES_MAP.get(key);
    }
}
