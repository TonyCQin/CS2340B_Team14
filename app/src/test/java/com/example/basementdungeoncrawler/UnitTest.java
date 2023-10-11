package com.example.basementdungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.lifecycle.ViewModelProvider;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.Player;
import com.example.basementdungeoncrawler.view.ConfigScreen;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;

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
        GameViewModel gameViewModel = new GameViewModel();

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
}