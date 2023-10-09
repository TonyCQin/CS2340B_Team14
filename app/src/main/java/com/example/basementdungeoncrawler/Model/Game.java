package com.example.basementdungeoncrawler.Model;

import android.os.CountDownTimer;
import android.widget.TextView;

public class Game {
    private int difficulty;
    private volatile static Game game;
    private int score;


    public Game(int difficulty, int score) {
        this.difficulty = difficulty;
        this.score = score;
    }

    public Game() {
        this(0, 60);
    }

    public static Game getGame() {
        if (game == null) {
            synchronized (Player.class) {
                if (game == null) {
                    game = new Game();
                }
            }
        }
        return game;
    }

    public void setDifficulty(int newDifficulty) {
        this.difficulty = newDifficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public int getScore() {
        return score;
    }
}
