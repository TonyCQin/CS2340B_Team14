package com.example.basementdungeoncrawler.Model;

import java.util.ArrayList;
import java.util.Collections;

public class ScoresList {
    private static volatile ScoresList list;
    //all recorded scores
    private ArrayList<Score> scores;
    //relevant scores
    private ArrayList<Score> topFive;
    private Score recentAttempt;

    private ScoresList() {
        scores = new ArrayList<>();
        topFive = new ArrayList<>();
        recentAttempt = new Score("placeholder", 0);
    }

    //retrieves an instance of ScoresList
    public static ScoresList getList() {
        if (list == null) {
            synchronized (ScoresList.class) {
                if (list == null) {
                    list = new ScoresList();
                }
            }
        }
        return list;
    }

    //adds new score to list to display on leaderboard and enforces descending order
    public synchronized void addScore(String username, int newScore) {
        Score score = new Score(username, newScore);
        recentAttempt = score;
        scores.add(score);
        scores.sort(Collections.reverseOrder());
        updateScores();
    }

    //retrieves relevant list of scores
    public synchronized ArrayList<Score> getScores() {
        return topFive;
    }
    public synchronized Score getRecentScore() {
        return recentAttempt;
    }

    //iterates through sorted list and adds the top five, stops if there are fewer than five scores
    private void updateScores() {
        topFive.clear();
        for (int i = 0; i < Math.min(5, scores.size()); i++) {
            topFive.add(scores.get(i));
        }
    }

    public void clearScores() {
        scores.clear();
        recentAttempt = new Score("placeholder", 0);
    }
}
