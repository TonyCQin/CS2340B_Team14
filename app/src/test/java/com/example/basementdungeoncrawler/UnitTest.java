package com.example.basementdungeoncrawler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import android.content.res.Resources;

import com.example.basementdungeoncrawler.Model.Score;
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

    @Before
    public void setUp() {
        Resources mockResources = mock(Resources.class);
    }

    public class MyResourceProvider {
        private final Resources resources;

        public MyResourceProvider(Resources resources) {
            this.resources = resources;
        }

        public Resources getResources() {
            return resources;
        }

        public int getDrawableId(String resourceName) {
            return resources.getIdentifier(resourceName, "drawable", "com.example.app");
        }
    }


//    @Test
//    public void testParsingXML() throws XmlPullParserException, IOException {
//        Context context = Mockito.mock(Context.class);
//        Resources resources = Mockito.mock(Resources.class);
//        AssetManager assetManager = Mockito.mock(AssetManager.class);
//        XmlResourceParser xmlParser = Mockito.mock(XmlResourceParser.class);
//
//        // Mock the context.getResources() to return the resources
//        Mockito.when(context.getResources()).thenReturn(resources);
//
//        // Mock the resources.getAssets() to return the assetManager
//        Mockito.when(resources.getAssets()).thenReturn(assetManager);
//
//        // Mock the assetManager.open() to return an InputStream
//        Mockito.when(assetManager.open(Mockito.anyString())).thenReturn(mock(InputStream.class));
//
//        // Mock the XmlPullParserFactory.newInstance()
//        XmlPullParserFactory factory = Mockito.mock(XmlPullParserFactory.class);
//        Mockito.when(XmlPullParserFactory.newInstance()).thenReturn(factory);
//
//        // Mock the factory.newPullParser() to return the xmlParser
//        Mockito.when(factory.newPullParser()).thenReturn(xmlParser);
//
//        // Mock the xmlParser methods as needed for your test
//        Mockito.when(xmlParser.getEventType())
//                .thenReturn(XmlResourceParser.START_DOCUMENT, XmlResourceParser.END_DOCUMENT);
//        Mockito.when(xmlParser.getName()).thenReturn("layer");
//        Mockito.when(xmlParser.getAttributeValue(Mockito.anyString(), Mockito.eq("name")))
//                .thenReturn("testLayer");
//        Mockito.when(xmlParser.nextText()).thenReturn("1,2,3,4,5");
//
//        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();
//        ArrayList<Integer> layer = new ArrayList<>();
//        layer.add(37);
//        layer.add(506);
//        layer.add(502);
//        layer.add(508);
//        layers.add(layer);
//        TmxParser parser = new TmxParser(context);
//        List<List<Integer>> parsedTileIds = parser.parseTmxFile(R.raw.test);
//        assertEquals(parsedTileIds.get(0).get(0), layer.get(0));
//        assertEquals(parsedTileIds.get(0).get(1), layer.get(1));
//        assertEquals(parsedTileIds.get(0).get(2), layer.get(2));
//        assertEquals(parsedTileIds.get(0).get(3), layer.get(3));
//    }
}