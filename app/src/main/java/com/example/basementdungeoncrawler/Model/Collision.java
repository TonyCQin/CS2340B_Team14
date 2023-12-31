package com.example.basementdungeoncrawler.Model;

//import android.util.Log;

import com.example.basementdungeoncrawler.graphics.TileMap;

import java.util.ArrayList;
import java.util.Arrays;

public class Collision implements PlayerSubscriber {
    private boolean right;
    private boolean left;
    private boolean bottom;
    private boolean up;
    private final ArrayList<Integer> tileWallIds = new ArrayList<>(Arrays.asList(0, 1, 2, 3,
        25, 26, 27, 28, 50, 51, 52, 53, 75, 76, 77, 78, 79, 100, 101, 102, 103, 104, 125, 126,
            127, 128, 129, 130, 131, 150, 151, 152, 153, 154, 350, 351, 352, 353, 354, 355, 356,
            357, 358, 359, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 400, 401, 402, 403,
            404, 405, 406, 407, 408, 451, 454, 457, 486, 185, 186, 210, 211));

    private final ArrayList<Integer> finishIds = new ArrayList<>(Arrays.asList(17, 18, 19, 42, 43,
        44, 67, 68, 69));

    private TileMap tileMap;


    public Collision(TileMap t) {
        tileMap = t;
    }


    public boolean getRight() {
        return right;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getBottom() {
        return bottom;
    }

    public boolean getUp() {
        return up;
    }

    public ArrayList<Integer> getTileWallIds() {
        return tileWallIds;
    }

    public void checkCollision(double posX, double posY) {
        posX += 16;
        posY += 16;
        int r = 48;
        //        Log.d("player tiles", String.format("right: %d, left: %d, up: %d, down: %d",
        //                getTileId(tileMap, posX + r, posY),
        //                getTileId(tileMap, posX - r, posY),
        //                getTileId(tileMap, posX, posY - r),
        //                getTileId(tileMap, posX, posY + r)
        //                ));
        right = tileWallIds.contains(getTileId(tileMap, posX + r, posY));
        left = tileWallIds.contains(getTileId(tileMap, posX - r, posY));
        bottom = tileWallIds.contains(getTileId(tileMap, posX, posY + r));
        up = tileWallIds.contains(getTileId(tileMap, posX, posY - r));
    }

    private int getTileId(TileMap tilemap, double positionX, double positionY) {
        return tilemap.getTile(positionX, positionY).getTileId();
    }

    @Override
    public void update(double positionX, double positionY) {
        checkCollision(positionX, positionY);
        checkGoalReached(positionX, positionY);
    }

    public void checkGoalReached(double x, double y) {
        GoalReached goalReached = GoalReached.getGoalReached();
        goalReached.setIsGoalReached(finishIds.contains(getTileId(tileMap, x, y)));
    }
}

