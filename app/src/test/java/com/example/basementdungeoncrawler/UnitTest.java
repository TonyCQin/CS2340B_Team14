package com.example.basementdungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.basementdungeoncrawler.Model.Score;
import com.example.basementdungeoncrawler.graphics.TmxLayer;
import com.example.basementdungeoncrawler.graphics.TmxParser;
import com.example.basementdungeoncrawler.view.ConfigScreen;
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
        assertTrue(ConfigScreen.isOnlyWhitespace(" "));
        assertTrue(ConfigScreen.isOnlyWhitespace("               " +
                " "));
    }

    @Test
    public void validUsernames() {
        assertFalse(ConfigScreen.isOnlyWhitespace("M ich e l l e"));
        assertFalse(ConfigScreen.isOnlyWhitespace("       Jeffrey"));
        assertFalse(ConfigScreen.isOnlyWhitespace("Tony"));
        assertFalse(ConfigScreen.isOnlyWhitespace("    n   "));
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
    public void correctScoresListLessThanFiveScores() throws IndexOutOfBoundsException {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.addListScore("player1", 100);
        gameViewModel.addListScore("player2", 25);

        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(100, (int) scores.get(0).getScore());
        assertEquals(25, (int) scores.get(1).getScore());
        assertThrows(IndexOutOfBoundsException.class, () -> scores.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> scores.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> scores.get(4));
    }

    @Test
    public void testAdapter() {
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("item 1");
        l1.add("item 2");
        l1.add("item 3");
        l1.add("item 4");
        l1.add("item 5");
        LeaderBoardAdapter testAdapter = new LeaderBoardAdapter(null, l1, l1);
        assertEquals(5, testAdapter.getCount());
    }

    @Test
    public void testRecentUsername() {
        EndScreenViewModel viewModel = new EndScreenViewModel();
        assertEquals("placeholder: 0", viewModel.getRecentUserNameAndScore());
    }

    @Test
    public void testNegativeScore() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.addListScore("player1", -5);
        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(-5, (int) scores.get(0).getScore());
    }

    @Test
    public void testManyPosAndNeg() {
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.addListScore("player1", -20);
        gameViewModel.addListScore("player2", -10);
        gameViewModel.addListScore("player3", 0);
        gameViewModel.addListScore("player2", -100);
        gameViewModel.addListScore("player1", 200);

        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(200, (int) scores.get(0).getScore());
        assertEquals(0, (int) scores.get(1).getScore());
        assertEquals(-10, (int) scores.get(2).getScore());
        assertEquals(-20, (int) scores.get(3).getScore());
        assertEquals(-100, (int) scores.get(4).getScore());
    }

//    @Test
//    public void testParsingXML() {
//        String sampleXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<map version=\"1.10\" tiledversion=\"1.10.2\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"32\" height=\"68\" tilewidth=\"16\" tileheight=\"16\" infinite=\"0\" nextlayerid=\"2\" nextobjectid=\"1\">\n" +
//                " <tileset firstgid=\"1\" source=\"Dungeon Prison.tsx\"/>\n" +
//                " <tileset firstgid=\"626\" source=\"DungeonTiles2.tsx\"/>\n" +
//                " <layer id=\"1\" name=\"Tile Layer 1\" width=\"32\" height=\"68\">\n" +
//                "  <data encoding=\"csv\">\n" +
//                "1138,1138,1138" +
//                "</data>\n" +
//                " </layer>\n" +
//                "</map>\n";
//        ArrayList<TmxLayer> layers = TmxParser.parseTmxFile(sampleXML);
//        ArrayList<TmxLayer> compareTo = new ArrayList<>();
//        TmxLayer layer1 = new TmxLayer("data");
//        layer1.setTileData("1138");
//        TmxLayer layer2 = new TmxLayer("data");
//        layer2.setTileData("1138");
//        TmxLayer layer3 = new TmxLayer("data");
//        layer3.setTileData("1138");
//
//        compareTo.add(layer1);
//        compareTo.add(layer2);
//        compareTo.add(layer3);
//        assertEquals(compareTo, layers);
//    }
}