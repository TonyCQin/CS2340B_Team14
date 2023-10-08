package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.Game;
public class GameViewModel extends ViewModel{
    private Game game;

    public GameViewModel() {
        game = Game.getGame();
    }

    public void setDifficulty(int newDifficulty) {
        game.setDifficulty(newDifficulty);
    }

    public int getDifficulty() {
        return game.getDifficulty();
    }
}
