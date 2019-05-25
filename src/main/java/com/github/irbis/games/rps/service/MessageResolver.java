package com.github.irbis.games.rps.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Service
public final class MessageResolver {
    private static final Map<String, String> MESSAGES_MAP_EN =
            Stream.of(new String[][] {
                    { "welcome", "Welcome to 'Rock Paper Scissors' game!" },
                    { "login", "Please enter your username or press <Enter> to quit:" },
                    { "select-action", "%s, please select an action:\n" },
                    { "start-new-game", "\t1 or r - start new game" },
                    { "display-statistic", "\t2 or j - display gaming statistic" },
                    { "exit-action-state", "\t0 or q - exit" },
                    { "gaming-statistic", "Gaming statistic" },
                    { "gaming-statistic-title", "\n username: score" },
                    { "gaming-statistic-line", "\n %s: %s " },
                    { "enter-to-continue", "\n\nPress <Enter> to continue" },
                    { "incorrect-command", "Incorrect command! Press Enter to continue!" },
                    { "lets-play", "Let's play %s!" },
                    { "make-choice", "Please make your choice:" },
                    { "rock-choice", "\t1 or a - rock" },
                    { "paper-choice", "\t2 or s - paper" },
                    { "scissor-choice", "\t3 or d - scissor" },
                    { "you-choice", "You choice is %s\n" },
                    { "my-choice", "My choice is %s\n" },
                    { "win", "You win :)" },
                    { "loose", "Sorry, you loose :(" },
                    { "draw", "We both are win ;)" },
                    { "exit", "Thanks for the game! Please, come back!" }
            }).collect(Collectors.collectingAndThen(
                    toMap(data -> data[0], data -> data[1]),
                    Collections::unmodifiableMap));

    private static final Map<String, String> MESSAGES_MAP_RU =
            Stream.of(new String[][] {
                    { "welcome", "Добро пожаловать в игру \"Камень, ножницы, бумага\"!" },
                    { "login", "Пожалуйста введите ваше имя или нажмите <Enter>, чтобы выйти из игры:" },
                    { "select-action", "%s, пожалуйста, сделайте выбор:\n" },
                    { "start-new-game", "\t1 or r - начало новой игры" },
                    { "display-statistic", "\t2 or j - вывести статистику игр" },
                    { "exit-action-state", "\t0 or q - выйти из игры" },
                    { "gaming-statistic", "Статистика игр" },
                    { "gaming-statistic-title", "\n имя игрока: баллы" },
                    { "gaming-statistic-line", "\n %s: %s " },
                    { "enter-to-continue", "\n\nНажмите <Enter> для продолжения!" },
                    { "incorrect-command", "Неверная команда! Нажмите <Enter> для продолжения!" },
                    { "lets-play", "Давайте сыграем %s!" },
                    { "make-choice", "Делайте ваш ход:" },
                    { "rock-choice", "\t1 or a - камень" },
                    { "paper-choice", "\t2 or s - бумага" },
                    { "scissor-choice", "\t3 or d - ножницы" },
                    { "you-choice", "Ваш выбор %s\n" },
                    { "my-choice", "Мой выбор %s\n" },
                    { "win", "Вы выиграли :)" },
                    { "loose", "Извините, вы проиграли :(" },
                    { "draw", "Мы оба выиграли ;)" },
                    { "exit", "Спасибо за игру! Приходите ещё!" }
            }).collect(Collectors.collectingAndThen(
                    toMap(data -> data[0], data -> data[1]),
                    Collections::unmodifiableMap));

    private final Map<String, String> currentMessagesMap;

    public MessageResolver() {
        Locale currentLocale = Locale.getDefault();

        if (currentLocale.getLanguage().equals(new Locale("ru").getLanguage())) {
            currentMessagesMap = MESSAGES_MAP_RU;
        } else {
            currentMessagesMap = MESSAGES_MAP_EN;
        }
    }

    public String getMessage(String key) {
        return currentMessagesMap.get(key);
    }
}
