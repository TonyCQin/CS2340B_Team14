package com.example.basementdungeoncrawler.Model;

public class Game {
    private int difficulty;
    private volatile static Game game;


    public Game(int difficulty) {
        this.difficulty = difficulty;
    }

    public Game() {
        this(0);
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
}
