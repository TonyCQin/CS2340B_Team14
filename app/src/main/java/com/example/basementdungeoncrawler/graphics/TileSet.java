package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import com.example.basementdungeoncrawler.R;

public class TileSet {
    private Bitmap bitmap;

    // hopefully in pixels
    private int tileSize;
    private int tileRows = 25;
    private int tileColumns = 25;

    public TileSet(Context context, int resourceID, int tileSize) {
        this.tileSize = tileSize;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceID, bitmapOptions);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Tile getTile(int tileId) {
        tileId -= 1;
        //getting row and column
        int row = Math.floorDiv(tileId, tileRows);
        int col = tileId % tileColumns;

        //making the rectangle of the sprite
        int left = col * tileSize;
        int right = (col + 1) * tileSize;
        int top = row * tileSize;
        int bottom = (row + 1) * tileSize;
        //returning the Tile with the bitmap?
        return new Tile(tileId, new Rect(left, top, right, bottom));
    }
}
