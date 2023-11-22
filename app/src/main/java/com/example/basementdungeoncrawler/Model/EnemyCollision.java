
package com.example.basementdungeoncrawler.Model;

import android.util.Log;

import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.graphics.TileMap;

import java.util.ArrayList;
import java.util.Arrays;

public class EnemyCollision implements PlayerSubscriber, EnemySubscriber {


    private boolean right;
    private boolean left;
    private boolean bottom;
    private boolean up;
    private boolean collideWithPlayer;

    private TileMap tileMap;
    private final ArrayList<Integer> tileWallIds = new ArrayList<>(Arrays.asList(0, 1, 2, 3,
            25, 26, 27, 28, 50, 51, 52, 53, 75, 76, 77, 78, 100, 101, 102, 103, 104, 125, 126, 127, 128,
            129, 130, 150, 151, 152, 153, 154, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 375,
            376, 377, 378, 379, 380, 381, 382, 383, 384, 400, 401, 402, 403, 404, 405, 406, 407, 408,
            451, 454, 457, 486, 185, 186, 210, 211));
    private double positionX;
    private double positionY;
    private double radius;

    private double playerPosX;
    private double playerPosY;
    private double playerR;

    private int speed;

    public EnemyCollision(TileMap t) {
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

    public boolean getCollideWithPlayer() {
        return collideWithPlayer;
    }

    @Override
    public void update(double positionX, double positionY) {
        this.playerPosX = positionX;
        this.playerPosY = positionY;
    }

    @Override
    public void updateEnemyPosition(double positionX, double positionY, double radius, int speed) {
        Log.d("updating to", String.format("x: %f, y: %f", positionX, positionY));
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        checkCollisionWithPlayer();
        checkCollisionWithWall(positionX, positionY);
    }

    private void checkCollisionWithPlayer() {
        if (Math.abs(this.playerPosX - this.positionX) < radius + playerR
            && Math.abs(this.playerPosY - this.positionY) < radius + playerR) {
            collideWithPlayer = true;
        } else {
            collideWithPlayer = false;
        }
        if (collideWithPlayer) {
            Log.d("Collide with player", "");
        }
    }

    private void checkCollisionWithWall(double posX, double posY) {
        posX += 32;
        posY += 16;
        Log.d("checking collision with", String.format("x: %f, y: %f", positionX, positionY));
        checkRight(posX, posY);
        checkLeft(posX, posY);
        checkDown(posX, posY);
        checkUp(posX, posY);
        Log.d("values", String.format("right: %b, up: %b, down: %b, left: %b", right, up, bottom, left));
    }

    private int getTileId(TileMap tilemap, double positionX, double positionY) {
        return tilemap.getEnemyTile(positionX, positionY).getTileId();
    }

    private void checkRight(double positionX, double positionY) {
        if (positionX  + radius > 960 || positionX + speed > 960) {
            right = true;
            return;
        }
        int tileByRadius = getTileId(tileMap, positionX + radius, positionY);
        int tileBySpeed = getTileId(tileMap, positionX + speed, positionY);
        Log.d("right by radius", String.valueOf(tileByRadius));
        Log.d("right by speed", String.valueOf(tileBySpeed));
        right = (tileWallIds.contains(tileByRadius) || tileWallIds.contains(tileBySpeed));
    }

    private void checkUp(double positionX, double positionY) {
        if (positionY - radius < 0 || positionY - speed < 0) {
            up = true;
            return;
        }
        int tileByRadius = getTileId(tileMap, positionX, positionY + radius);
        int tileBySpeed = getTileId(tileMap, positionX, positionY + speed);
        Log.d("up by radius", String.valueOf(tileByRadius));
        Log.d("up by speed", String.valueOf(tileBySpeed));
        up = (tileWallIds.contains(tileByRadius) || tileWallIds.contains(tileBySpeed));
    }

    private void checkLeft(double positionX, double positionY) {
        if (positionX - radius < 0 || positionX - speed < 0) {
            left = true;
            return;
        }
        int tileByRadius = getTileId(tileMap, positionX - radius, positionY);
        int tileBySpeed = getTileId(tileMap, positionX - speed, positionY);
        Log.d("left by radius", String.valueOf(tileByRadius));
        Log.d("left by speed", String.valueOf(tileBySpeed));
        left =  (tileWallIds.contains(tileByRadius) || tileWallIds.contains(tileBySpeed));
    }

    private void checkDown(double positionX, double positionY) {
        if (positionY + radius > 1900 || positionY + speed > 1900) {
            Log.d("bottom edge", "");
            bottom = true;
            return;
        }
        int tileByRadius = getTileId(tileMap, positionX, positionY - radius);
        int tileBySpeed = getTileId(tileMap, positionX, positionY - speed);
        Log.d("down by radius", String.valueOf(tileByRadius));
        Log.d("down by speed", String.valueOf(tileBySpeed));
        bottom = (tileWallIds.contains(tileByRadius) || tileWallIds.contains(tileBySpeed));
    }
}


