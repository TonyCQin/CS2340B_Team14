package com.example.basementdungeoncrawler.Model;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Score implements Comparable<Score> {
    private String username;
    private int score;
    private String timeOfAttempt;

    Score(String username, int score) {
        this.username = username;
        this.score = score;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.timeOfAttempt = dateFormat.format(calendar.getTime());
    }

    @Override
    public int compareTo(Score other) {
        return this.score - other.score;
    }

    public String getUsername() {
        return username;
    }

    public String getTimeOfAttempt() {
        return timeOfAttempt;
    }

    public int getScore() {
        return score;
    }
}
