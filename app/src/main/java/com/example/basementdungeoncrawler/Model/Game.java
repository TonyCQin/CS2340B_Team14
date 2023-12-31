package com.example.basementdungeoncrawler.Model;
public class Game {
    private int difficulty;
    private static volatile Game game;
    private int score;
    private int screenCounter;


    public Game(int difficulty, int score) {
        this.difficulty = difficulty;
        this.score = score;
    }

    public Game() {
        this(0, 60);
    }

    public static Game getGame() {
        if (game == null) {
            synchronized (PlayerData.class) {
                if (game == null) {
                    game = new Game();
                }
            }
        }
        return game;
    }

    public void setDifficulty(int newDifficulty) {
        this.difficulty = newDifficulty;
        setPlayerHP(difficulty);
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

    public void setScreenCounter(int newScreenCounter) {
        screenCounter = newScreenCounter;
    }

    public int getScreenCounter() {
        return screenCounter;
    }

    private void setPlayerHP(int difficulty) {
        if (difficulty == 3) {
            PlayerData.getPlayer().setHp(100);
        } else if (difficulty == 2) {
            PlayerData.getPlayer().setHp(150);
        } else if (difficulty == 1) {
            PlayerData.getPlayer().setHp(200);
        }
    }
}
