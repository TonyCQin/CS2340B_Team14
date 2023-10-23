package com.example.basementdungeoncrawler.Model;

import android.util.Log;

import com.example.basementdungeoncrawler.graphics.TileMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collision implements PlayerSubscriber {
    private boolean right;
    private boolean left;
    private boolean bottom;
    private boolean up;
    private final ArrayList<Integer> tileWallIds = new ArrayList<>(Arrays.asList(0, 1, 2, 3,
        25, 26, 27, 28, 50, 51, 52, 53, 75, 76, 77, 78, 100, 101, 102, 103, 104, 125, 126, 127, 128,
        129, 150, 151, 152, 153, 154, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 375, 376,
        377, 378, 379, 380, 381, 382, 383, 384, 400, 401, 402, 403, 404, 405, 406, 407, 408, 451,
        454, 457, 486, 185, 186, 210, 211));

    private TileMap tileMap;
    private double positionX;
    private double positionY;


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

    public void checkCollision(double posX, double posY, double r) {
        right = tileWallIds.contains(getTileId(tileMap, posX + r, posY));
        left = tileWallIds.contains(getTileId(tileMap, posX - r, posY));
        bottom = tileWallIds.contains(getTileId(tileMap, posX, posY + r));
        up = tileWallIds.contains(getTileId(tileMap, posX, posY - r));
//        Log.d("right tile", String.valueOf(getTileId(tileMap, posX + r, posY)));
//        Log.d("left tile", String.valueOf(getTileId(tileMap, posX - r, posY)));
//        Log.d("bottom tile", String.valueOf(getTileId(tileMap, posX, posY + r)));
//        Log.d("up tile", String.valueOf(getTileId(tileMap, posX, posY - r)));
        positionX = posX;
        positionY = posY;
    }

    private int getTileId(TileMap tilemap, double positionX, double positionY) {
//        Log.d("tileid", String.valueOf(tilemap.getTile(positionX, positionY).getTileId()));
        return tilemap.getTile(positionX, positionY).getTileId();
    }

    @Override
    public void update(double positionX, double positionY, double radius) {
        checkCollision(positionX, positionY, radius);
    }
}

