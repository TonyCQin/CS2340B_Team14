package com.example.basementdungeoncrawler.Model;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesProvider;

public class Score implements Comparable<Score> {
    private String username;
    private int score;
    private ZonedDateTime timeOfAttempt;

    Score(String username, int score) {
        ZoneRulesProvider.registerProvider(new TzdbZoneRulesProvider());
        this.username = username;
        this.score = score;
        this.timeOfAttempt = ZonedDateTime.now();
    }

    @Override
    public int compareTo(Score other) {
        return this.score - other.score;
    }

    public String getUsername() {
        return username;
    }

    public ZonedDateTime getTimeOfAttempt() {
        return timeOfAttempt;
    }

    public int getScore() {
        return score;
    }
}
