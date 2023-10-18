package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.R;

public class TileSet {
    private Bitmap tilesetImage;
    private int tileWidth = 16;
    private int tileHeight = 16;
    private int columns;
    private int rows;

    public TileSet(Bitmap tilesetImage, int tileWidth, int tileHeight) {
        //tileWidth, height in pixels
        this.tilesetImage = tilesetImage;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.columns = tilesetImage.getWidth() / tileWidth;
        this.rows = tilesetImage.getHeight() / tileHeight;
    }

    public Bitmap getTile(int tileId) {
        if (tileId > 626) {
            tileId -= 626;
        } else if (tileId > 1){
            tileId -= 1;
        }
        int col = tileId % columns;
        int row = tileId / columns;

        int startX = col * tileWidth;
        int startY = row * tileHeight;

        return Bitmap.createBitmap(tilesetImage, startX, startY, tileWidth, tileHeight);
    }
}
