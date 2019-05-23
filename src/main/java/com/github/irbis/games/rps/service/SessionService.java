package com.github.irbis.games.rps.service;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private String user = "";

    public void createSession(String username) {
        user = username;
    }

    public String getCurrentSession() {
        return user;
    }
}
