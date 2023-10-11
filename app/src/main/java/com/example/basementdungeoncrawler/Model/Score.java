package com.example.basementdungeoncrawler.Model;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesProvider;

public class Score implements Comparable<Score> {
    private String username;
    private int score;
    private ZonedDateTime timeOfAttempt;

    Score(String username, int score) {
//        ZoneRulesProvider.registerProvider(new TzdbZoneRulesProvider()); caused issues with unit tests
        this.username = username;
        this.score = score;
        this.timeOfAttempt = ZonedDateTime.now();
    }

    @Override
    public int compareTo(Score other) {
        return this.score - other.score;
    }

    public int getScore() { return this.score; }
}
