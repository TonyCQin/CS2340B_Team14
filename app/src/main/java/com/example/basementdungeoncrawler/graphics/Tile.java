package com.example.basementdungeoncrawler.graphics;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Tile {
    private int tileId;
    private Rect rect;
    private double centerX;
    private double centerY;

    public Tile(int tileId, Rect rect) {
        this.tileId = tileId;
        this.rect = rect;
    }

    public Tile() {
        this(0, new Rect());
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int newTileId) {
        tileId = newTileId;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect newRect) {
        rect = newRect;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double newCenterX) {
        centerX = newCenterX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double newCenterY) {
        centerY = newCenterY;
    }
}

