package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.Model.Score;

import java.util.ArrayList;

public class EndScreenViewModel extends ViewModel {
    private final ScoresList scoreslist = ScoresList.getList();
    private ArrayList<Score> top5 = new ArrayList<>();
    private ArrayList<String> userNamesAndScore = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private Score recent;

    public void setUpUserNamesAndScores() {
        top5 = scoreslist.getScores();
        userNamesAndScore = new ArrayList<>();
        for (int i = 0; i < top5.size(); i++) {
            Score cur = top5.get(i);
            userNamesAndScore.add(cur.getUsername() + ": " + cur.getScore());
        }
    }
    public ArrayList<String> getUserNamesAndScores() {
        setUpUserNamesAndScores();
        return userNamesAndScore;
    }

    public void setUpTime() {
        top5 = scoreslist.getScores();
        time = new ArrayList<>();
        for (int i = 0; i < top5.size(); i++) {
            Score cur = top5.get(i);
            time.add(cur.getTimeOfAttempt());
        }
    }

    public ArrayList<String> getTime() {
        setUpTime();
        return time;
    }

    public String getRecentUserNameAndScore() {
        recent = scoreslist.getRecentScore();
        return recent.getUsername() + ": " + recent.getScore();
    }

    public String getRecentTime() {
        recent = scoreslist.getRecentScore();
        return recent.getTimeOfAttempt();
    }

    public void clearUserNameAndScores() {
        userNamesAndScore.clear();
    }
}
