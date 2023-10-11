package com.example.basementdungeoncrawler.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.example.basementdungeoncrawler.Model.Score;
//time imports
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesProvider;
import org.threeten.bp.ZonedDateTime;

public class ScoresList {
    private volatile static ScoresList list;
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
        Collections.sort(scores, Collections.reverseOrder());
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

    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // Swap elements if they are in the wrong order
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
