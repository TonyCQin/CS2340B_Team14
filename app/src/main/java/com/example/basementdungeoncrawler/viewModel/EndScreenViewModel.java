package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.Model.Score;

import java.util.ArrayList;

public class EndScreenViewModel extends ViewModel {
    private final ScoresList scoreslist = ScoresList.getList();
    private ArrayList<Score> top5 = new ArrayList<>();
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> scoreAndTime = new ArrayList<>();
    private Score recent;

    public void setUpUserNames() {
        top5 = scoreslist.getScores();
        userNames = new ArrayList<>();
        for (int i = 0; i < top5.size(); i++) {
            Score cur = top5.get(i);
            userNames.add(cur.getUsername());
        }
    }
    public ArrayList<String> getUserNames() {
        setUpUserNames();
        return userNames;
    }

    public void setUpScoreAndTime() {
        top5 = scoreslist.getScores();
        scoreAndTime = new ArrayList<>();
        for (int i = 0; i < top5.size(); i++) {
            Score cur = top5.get(i);
            scoreAndTime.add("" + cur.getScore() + " at " + cur.getTimeOfAttempt().toString());
        }
    }

    public ArrayList<String> getScoreAndTime() {
        setUpScoreAndTime();
        return scoreAndTime;
    }

    public String getRecentUserName() {
        recent = scoreslist.getRecentScore();
        return recent.getUsername();
    }

    public String getRecentScoreAndTime() {
        recent = scoreslist.getRecentScore();
        return "" + recent.getScore() + " at " + recent.getTimeOfAttempt().toString();
    }
}
