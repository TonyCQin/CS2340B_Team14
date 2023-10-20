package com.example.basementdungeoncrawler.view;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.graphics.TileSet;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import java.util.List;

public class Map2View extends View{
    private Tile[][] tileMap;
    private TileSet dungeonTileSet;
    private TileSet propTileSet;
    private int screenWidth;
    private int screenHeight;
    private int tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
    private int tileHeight = screenHeight / NUMBER_OF_ROW_TILES;

    public Map2View(Context context, Tile[][] tileMap) {
        super(context);
        this.tileMap = tileMap;
        dungeonTileSet = new TileSet(context, R.drawable.tiles2, 16);
        propTileSet = new TileSet(context, R.drawable.props, 16);

        Resources resources = context.getResources();
        screenHeight = resources.getDisplayMetrics().heightPixels;
        screenWidth = resources.getDisplayMetrics().widthPixels;
        tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
        tileHeight = screenHeight / NUMBER_OF_ROW_TILES;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        renderLayer(canvas, tileMap);
        super.onDraw(canvas);
    }

    private Rect drawDestRect(int row, int col) {
        Log.d("row", String.valueOf(row));
        Log.d("col", String.valueOf(col));
        return new Rect(col * tileWidth,
                row * tileHeight,
                (col + 1) * tileWidth,
                (row + 1) * tileHeight
        );
    }

    private void renderLayer(Canvas canvas, Tile[][] layer) {
        for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                Tile tile = layer[row][col];
                Rect srcRect = tile.getRect();
                Rect destRect = drawDestRect(row, col);
                Log.d("srcRect", String.valueOf(srcRect));
                Log.d("destRect", String.valueOf(destRect));
                int tileId = tile.getTileId();
                if (tileId >= 625) {
                    canvas.drawBitmap(propTileSet.getBitmap(), srcRect, destRect, null);
                } else {
                    canvas.drawBitmap(dungeonTileSet.getBitmap(), srcRect, destRect, null);
                }
            }
        }
    }
}
