
package com.example.basementdungeoncrawler.Model;

import com.example.basementdungeoncrawler.graphics.TileMap;

import java.util.ArrayList;
import java.util.Arrays;

public class EnemyCollision implements PlayerSubscriber, EnemySubscriber {

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

    public EnemyCollision(TileMap t) {
        tileMap = t;
    }
    @Override
    public void update(double positionX, double positionY, double radius) {
        this.playerPosX = positionX;
        this.playerPosY = positionY;
        this.playerR = radius;
        checkCollisionWithPlayer();
    }

    @Override
    public void updateEnemyPosition(double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        checkCollisionWithPlayer();
        checkCollisionWithWall();
    }

    private void checkCollisionWithPlayer() {
        if (Math.abs(this.playerPosX - this.positionX) < radius + playerR
            && Math.abs(this.playerPosY - this.positionY) < radius + playerR ) {
            collideWithPlayer = true;
        }
    }

    private void checkCollisionWithWall() {
        right = tileWallIds.contains(getTileId(tileMap, this.positionX + radius, this.positionY));
        left = tileWallIds.contains(getTileId(tileMap, this.positionX - radius, this.positionY));
        bottom = tileWallIds.contains(getTileId(tileMap, this.positionX, this.positionY + radius));
        up = tileWallIds.contains(getTileId(tileMap, this.positionX, this.positionY - radius));
    }

    private int getTileId(TileMap tilemap, double positionX, double positionY) {
        return tilemap.getTile(positionX, positionY).getTileId();
    }
}
