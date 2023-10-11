package com.example.basementdungeoncrawler.viewModel;

import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.ScoresList;
import java.util.ArrayList;

public class GameViewModel extends ViewModel {
    private Game game;
    private ScoresList list;

    public GameViewModel() {
        game = Game.getGame();
        list = ScoresList.getList();
    }

    public void setDifficulty(int newDifficulty) {
        game.setDifficulty(newDifficulty);
    }

    public int getDifficulty() {
        return game.getDifficulty();
    }

    public void setScore(int newScore) {
        game.setScore(newScore);
    }

    public void addListScore(int finalScore) {
        list.addScore(finalScore);
    }

    //newest score for leaderboard display
    public int getScore() {
        return game.getScore();
    }

    //top five scores for leaderboard display
    public ArrayList<Integer> getScoresList() {
        return list.getScores(); }

}