package com.example.basementdungeoncrawler.graphics;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Tile {
    private int tileId;
    private Rect rect;

    public Tile(int tileId, Rect rect) {
        this.tileId = tileId;
        this.rect = rect;
    }

    public int getTileId() {
        return tileId;
    }

    public Rect getRect() {
        return rect;
    }

}

