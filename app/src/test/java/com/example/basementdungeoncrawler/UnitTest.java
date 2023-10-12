package com.example.basementdungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import androidx.lifecycle.ViewModelProvider;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.Player;
import com.example.basementdungeoncrawler.Model.Score;
import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.view.ConfigScreen;
import com.example.basementdungeoncrawler.view.EndScreen;
import com.example.basementdungeoncrawler.viewModel.EndScreenViewModel;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.LeaderBoardAdapter;
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
    public void noEmptyUsernames() {
        assertEquals(ConfigScreen.isOnlyWhitespace(" "), true);
        assertEquals(ConfigScreen.isOnlyWhitespace("               " +
                " "), true);
    }

    @Test
    public void validUsernames() {
        assertEquals(ConfigScreen.isOnlyWhitespace("M ich e l l e"), false);
        assertEquals(ConfigScreen.isOnlyWhitespace("       Jeffrey"), false);
        assertEquals(ConfigScreen.isOnlyWhitespace("Tony"), false);
        assertEquals(ConfigScreen.isOnlyWhitespace("    n   "), false);
    }
      
    @Test
    public void correctScoresList() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.addListScore("player1", 100);
        gameViewModel.addListScore("player2", 25);
        gameViewModel.addListScore("player3", 75);
        gameViewModel.addListScore("player2", 50);
        gameViewModel.addListScore("player1", 200);
        gameViewModel.addListScore("player3", 150);

        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(200, (int) scores.get(0).getScore());
        assertEquals(150, (int) scores.get(1).getScore());
        assertEquals(100, (int) scores.get(2).getScore());
        assertEquals(75, (int) scores.get(3).getScore());
        assertEquals(50, (int) scores.get(4).getScore());
    }


    @Test
    public void correctScoresListLessThanFiveScores() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.addListScore("player1", 100);
        gameViewModel.addListScore("player2", 25);

        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(100, (int) scores.get(0).getScore());
        assertEquals(25, (int) scores.get(1).getScore());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            scores.get(2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            scores.get(3);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            scores.get(4);
        });
    }

    @Test
    public void testAdapter() {
        LeaderBoardAdapter testAdapter = new LeaderBoardAdapter(null,
                new ArrayList<String>(), new ArrayList<String>());
        assertEquals(0, testAdapter.getCount());
    }

    @Test
    public void testRecentUsername() throws IllegalAccessException, InstantiationException {
        EndScreenViewModel endScreenViewModel =
            new ViewModelProvider(EndScreen.class.newInstance()).get(EndScreenViewModel.class);
        assertEquals("placeholder", endScreenViewModel.getRecentUserName());
    }
}