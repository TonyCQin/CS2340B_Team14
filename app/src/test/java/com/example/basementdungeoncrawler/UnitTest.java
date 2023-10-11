package com.example.basementdungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.lifecycle.ViewModelProvider;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.Player;
import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;
import java.util.ArrayList;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void correctDifficulty() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.setDifficulty(2);
        assertEquals(playerViewModel.getHP(), 150);

        gameViewModel.setDifficulty(1);
        assertEquals(playerViewModel.getHP(), 200);

        gameViewModel.setDifficulty(3);
        assertEquals(playerViewModel.getHP(), 100);
    }

    @Test
    public void correctSprite() {
        PlayerViewModel playerViewModel = new PlayerViewModel();

        playerViewModel.setSprite(1);
        assertEquals(R.drawable.idle_crop1, playerViewModel.getSprite());

        playerViewModel.setSprite(2);
        assertEquals(R.drawable.pumpkin_crop, playerViewModel.getSprite());

        playerViewModel.setSprite(3);
        assertEquals(R.drawable.doc_crop, playerViewModel.getSprite());
    }

    @Test
    public void correctScoresList() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.setScore(100);
        gameViewModel.setScore(25);
        gameViewModel.setScore(75);
        gameViewModel.setScore(50);
        gameViewModel.setScore(200);
        gameViewModel.setScore(150);

        ArrayList<Integer> scores = gameViewModel.getScoresList();
        assertEquals(200, (int) scores.get(0));
        assertEquals(150, (int) scores.get(1));
        assertEquals(100, (int) scores.get(2));
        assertEquals(75, (int) scores.get(3));
        assertEquals(50, (int) scores.get(4));
    }

    @Test
    public void correctScoresListLessThanFiveScores() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.setScore(100);
        gameViewModel.setScore(25);

        ArrayList<Integer> scores = gameViewModel.getScoresList();
        assertEquals(100, (int) scores.get(0));
        assertEquals(25, (int) scores.get(1));
        //unsure if there's a simpler way to do this, apologies for ugly
        try {
            scores.get(2);
            throw new AssertionError("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
        try {
            scores.get(3);
            throw new AssertionError("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
        try {
            scores.get(4);
            throw new AssertionError("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
    }
}