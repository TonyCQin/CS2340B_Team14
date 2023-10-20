package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.ScoresList;
import java.util.ArrayList;
import com.example.basementdungeoncrawler.Model.Score;

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

    //newest score for leaderboard display
    public void addListScore(String username, int finalScore) {
        list.addScore(username, finalScore); }

    public int getScore() {
        return game.getScore(); }

    //top five scores for leaderboard display
    public ArrayList<Score> getScoresList() {
        return list.getScores(); }

    public void clearScores() {
        list.clearScores();
    }
}