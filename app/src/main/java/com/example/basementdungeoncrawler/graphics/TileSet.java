package com.example.basementdungeoncrawler.graphics;

import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

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

    public Tile getTile(Context context, int tileId) {
        Resources resources = context.getResources();
        int screenHeight = resources.getDisplayMetrics().heightPixels;
        int screenWidth = resources.getDisplayMetrics().widthPixels;
        int tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
        int tileHeight = screenHeight / NUMBER_OF_ROW_TILES;
        if (tileId >= 626) {
            tileId -= 626;
            //getting row and column
            int row = Math.floorDiv(tileId, tileRows);
            int col = tileId % tileColumns;

            //making the rectangle of the sprite
            int left = col * tileSize;
            int right = (col + 1) * tileSize;
            int top = row * tileSize;
            int bottom = (row + 1) * tileSize;
            //returning the Tile with the bitmap?
            Tile tile = new Tile(tileId + 626, new Rect(left, top, right, bottom));
            tile.setCenterX(tileWidth * (col + 0.5));
            tile.setCenterY(tileHeight * (row + 0.5));
            return tile;
        } else {
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
            Tile tile = new Tile(tileId + 1, new Rect(left, top, right, bottom));
            tile.setCenterX(tileWidth * (col + 0.5));
            tile.setCenterY(tileHeight * (row + 0.5));
            return tile;
        }
    }
}
