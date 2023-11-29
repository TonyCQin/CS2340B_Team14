package com.example.basementdungeoncrawler;


import static com.example.basementdungeoncrawler.Model.EnemyCollision.getTileId;

import org.junit.Before;

import org.junit.Assert;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import com.example.basementdungeoncrawler.Model.EdgeReached;
import com.example.basementdungeoncrawler.Model.Collision;
import com.example.basementdungeoncrawler.Model.GoalReached;
import com.example.basementdungeoncrawler.Model.PlayerData;
import com.example.basementdungeoncrawler.Model.Score;
import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.Model.powerups.HPPowerUp;
import com.example.basementdungeoncrawler.Model.powerups.PowerUpNotifier;
import com.example.basementdungeoncrawler.Model.powerups.SpeedPowerUp;
import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.view.ConfigScreen;
import com.example.basementdungeoncrawler.viewModel.EndScreenViewModel;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.LeaderBoardAdapter;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;

import java.util.ArrayList;
import java.util.Arrays;

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
        assertEquals(R.drawable.knight_sheet, playerViewModel.getSprite());

        playerViewModel.setSprite(2);
        assertEquals(R.drawable.rogue_sheet, playerViewModel.getSprite());

        playerViewModel.setSprite(3);
        assertEquals(R.drawable.wizard_sheet, playerViewModel.getSprite());
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
        gameViewModel.clearListScores();
        gameViewModel.clearScores();

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
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.clearListScores();

        gameViewModel.clearScores();
        assertEquals("placeholder: 0", viewModel.getRecentUserNameAndScore());
    }

    @Test
    public void testNegativeScore() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.clearListScores();
        gameViewModel.clearScores();
        gameViewModel.addListScore("player1", -5);
        ArrayList<Score> scores = gameViewModel.getScoresList();
        assertEquals(-5, (int) scores.get(0).getScore());
    }

    @Test
    public void testManyPosAndNeg() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.clearListScores();
        gameViewModel.clearScores();

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

    @Test
    public void testReturningTileCenterX() {
        Tile tile = new Tile();
        tile.setCenterX(15);
        tile.setCenterY(123);
        assertEquals((double)15, tile.getCenterX(), 0);
    }

    @Test
    public void testReturningTileCenterY() {
        Tile tile = new Tile();
        tile.setCenterX(15);
        tile.setCenterY(123);
        assertEquals((double)123, tile.getCenterY(), 0);
    }


    @Test
    public void testCollisionWalls() {
        Collision collision = new Collision(null);
        assertTrue(collision.getTileWallIds().contains(0));
        assertFalse(collision.getTileWallIds().contains(10));
        assertFalse(collision.getTileWallIds().size() == 68);
    }

    @Test
    public void testCollisionInstantiation() {
        Collision collision = new Collision(null);
        assertFalse(collision.getRight());
        assertFalse(collision.getLeft());
        assertFalse(collision.getBottom());
        assertFalse(collision.getUp());
        assertFalse(collision.getTileWallIds() == null);
    }


    @Test
    public void testPositionXGetterSetter() {
        PlayerViewModel player = new PlayerViewModel();
        double expectedPositionX = 10.0;
        player.setX(expectedPositionX);
        assertEquals(expectedPositionX, player.getX(), 1.0);
    }

    @Test
    public void testPositionYGetterSetter() {
        PlayerViewModel player = new PlayerViewModel();
        double expectedPositionY = 10.0;
        player.setY(expectedPositionY);
        assertEquals(expectedPositionY, player.getY(), 1.0);
    }

    @Test
    public void testSetGetHP() {
        PlayerViewModel player = new PlayerViewModel();
        player.setHP(45);
        assertEquals(45, player.getHP());
    }

    @Test
    public void testSetGetScreenCounter() {
        GameViewModel test = new GameViewModel();
        test.setScreenCounter(2);
        assertEquals(2, test.getScreenCounter());
    }
    @Test
    public void testIsEdgeReachedGetter() {
        EdgeReached check = EdgeReached.getEdgeReached();
        assertEquals(false, check.getIsEdgeReached());
    }

    @Test
    public void testIsGoalReachedGetter() {
        GoalReached check = GoalReached.getGoalReached();
        assertEquals(false, check.getIsGoalReached());
    }
    @Test
    public void testGetDifficulty(){
        GameViewModel a = new GameViewModel();
        a.setDifficulty(1);
        assertEquals(a.getDifficulty(), 1);
    }
    @Test
    public void testSetScore(){
        GameViewModel a = new GameViewModel();
        a.setScore(5);
        assertEquals(a.getScore(), 5);
    }

    @Test
    public void enemyPlayerCollisionFalse() {
        double playerPosX = 10;
        double playerPosY = 10;
        double playerRadius = 1.0;

        double posX = 0.0;
        double posY = 0.0;
        double radius = 1.0;

        boolean collideWithPlayer = false;
        if (Math.abs(playerPosX - posX) < radius + playerRadius
            && Math.abs(playerPosY - posY) < radius + playerRadius) {
            collideWithPlayer = true;
        }
        assertFalse(collideWithPlayer);
    }

    @Test
    public void enemyPlayerCollisionTrue() {
        double playerPosX = 1.0;
        double playerPosY = 1.0;
        double playerRadius = 1.0;

        double posX = 0.0;
        double posY = 0.0;
        double radius = 1.0;

        boolean collideWithPlayer = false;
        if (Math.abs(playerPosX - posX) < radius + playerRadius
            && Math.abs(playerPosY - posY) < radius + playerRadius) {
            collideWithPlayer = true;
        }
        assertTrue(collideWithPlayer);
    }

    @Test
    public void testGetDifficulty2(){
        GameViewModel a = new GameViewModel();
        a.setDifficulty(0);
        assertEquals(a.getDifficulty(), 0);
    }

    @Test
    public void testGetDifficulty3(){
        GameViewModel a = new GameViewModel();
        a.setDifficulty(6);
        assertEquals(a.getDifficulty(), 6);
    }

    @Test
    public void testSetUsername() {
        PlayerViewModel p = new PlayerViewModel();
        p.setUsername("a");
        assertEquals(p.getUsername(), "a");
    }

    @Test
    public void testClearingScores() {
        EndScreenViewModel endScreenViewModel = new EndScreenViewModel();
        endScreenViewModel.clearUserNameAndScores();
        assertEquals(5, endScreenViewModel.getUserNamesAndScores().size());
    }

    @Test
    public void testClearScores() {
        ScoresList list = ScoresList.getList();
        list.addScore("username", 100);
        list.clearScores();
        assertEquals(list.getRecentScore().getUsername(), "placeholder");
        assertEquals(list.getRecentScore().getScore(), 0);
    }

    @Test
    public void testEndScreenSetUp() {
        EndScreenViewModel end = new EndScreenViewModel();
        end.setUpUserNamesAndScores();
        ArrayList<String> nameAndScore = new ArrayList<>();
        assertEquals(end.getUserNamesAndScores(), nameAndScore);
    }

    @Test
    public void collisionCheckWithArray() {
        ArrayList<Integer> tileWallIds = new ArrayList<>(Arrays.asList(0));
        int x = 0;
        int r = 2;
        int y = 0;
        int s = 1;
        assertTrue(tileWallIds.contains(getTileId(x+r, y)) || tileWallIds.contains(getTileId(
            x + s, y))) ;
    }

    @Test
    public void collisionCheckWithArrayFalse() {
        ArrayList<Integer> tileWallIds = new ArrayList<>(Arrays.asList(1));
        int x = 1;
        int r = 1;
        int y = 10;
        int s = 5;
        assertFalse(tileWallIds.contains(getTileId(x+r, y)) || tileWallIds.contains(getTileId(
            x + s, y))) ;
    }



    @Test
    public void settingSpeed() {
        PlayerData player = PlayerData.getPlayer();
        player.setSpeed(2);
        assertEquals(2, player.getSpeed(), 0);
        player.setSpeed(4);
        assertEquals(4, player.getSpeed(), 0);
        player.setSpeed(6);
        assertEquals(6, player.getSpeed(), 0);
    }

    @Test
    public void radiusSetting() {
        PlayerData player = PlayerData.getPlayer();
        player.setRadius(15);
        player.setRadius(20);
        assertEquals(player.getRadius(), 20, 0);
        player.setRadius(15);
        assertEquals(player.getRadius(), 15, 0);
    }

    @Test
    public void testPowerUp() {
        PowerUpNotifier notifier = new PowerUpNotifier();
        HPPowerUp powerUp = new HPPowerUp(0, 0, 20, notifier);
        powerUp.setClaimed(true);
        assertTrue(powerUp.getClaimed());
    }

}