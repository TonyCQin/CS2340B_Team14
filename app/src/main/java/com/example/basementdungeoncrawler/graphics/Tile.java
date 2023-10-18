package com.example.basementdungeoncrawler.graphics;
import android.graphics.Bitmap;

public class Tile {
    private int tileId;
    private Bitmap bitmap;

    public Tile(int tileId, Bitmap bitmap) {
        this.tileId = tileId;
        this.bitmap = bitmap;
    }

    public int getTileId() {
        return tileId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}

