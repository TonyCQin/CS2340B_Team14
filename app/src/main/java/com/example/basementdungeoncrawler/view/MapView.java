package com.example.basementdungeoncrawler.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.graphics.TileSet;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import java.util.ArrayList;

public class MapView extends View{
    private ArrayList<Tile[][]> layers;
    private TileSet dungeonTileSet;
    private TileSet propTileSet;
    private int screenWidth;
    private int screenHeight;
    private int tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
    private int tileHeight = screenHeight / NUMBER_OF_ROW_TILES;

    public MapView(Context context, ArrayList<Tile[][]> layers) {
        super(context);
        this.layers = layers;
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
        for (Tile[][] layer : layers) {
            renderLayer(canvas, layer);
        }

        super.onDraw(canvas);
    }

    private Rect drawDestRect(int row, int col) {
        return new Rect(col * tileWidth,
                row * tileHeight,
                (col + 1) * tileWidth,
                (row + 1) * tileHeight
                );
    }

    private void renderLayer(Canvas canvas, Tile[][] layer) {
        for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                Tile toDraw = layer[row][col];
                drawTile(canvas, toDraw, row, col);
            }
        }
    }

    private void drawTile(Canvas canvas, Tile tile, int row, int col) {
        Rect srcRect = tile.getRect();
        Rect destRect = drawDestRect(row, col);
        int tileId = tile.getTileId();
        Log.d("srcRect", String.valueOf(srcRect));
        Log.d("destRect", String.valueOf(destRect));
        Log.d("tileID", String.valueOf(tileId));
        if (tileId > 0) {
            canvas.drawBitmap(dungeonTileSet.getBitmap(), srcRect, destRect, null);
//                    Log.d("srcRect", String.valueOf(srcRect));
//                    Log.d("destRect", String.valueOf(destRect));
//                    Log.d("tileID", String.valueOf(tileId));
//                    Log.d("tileAdded", "dungeon tile");
        }
        if (tileId >= 626) {
            canvas.drawBitmap(propTileSet.getBitmap(), srcRect, destRect, null);
//                    Log.d("srcRect", String.valueOf(srcRect));
//                    Log.d("destRect", String.valueOf(destRect));
//                    Log.d("tileID", String.valueOf(tileId));
//                    Log.d("tileAdded", "prop tile");
        }

        if (tileId <= 0) {
//                        Log.d("tileID", String.valueOf(tileId));
            int width = 16; // Width of the Bitmap
            int height = 16; // Height of the Bitmap

            Bitmap redBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas testCanvas = new Canvas(redBitmap);

            Paint paint = new Paint();
            paint.setColor(Color.TRANSPARENT);
            paint.setAlpha(0);

            testCanvas.drawRect(0, 0, width, height, paint);
            Rect placeholderRect = new Rect(0,0,16,16);
            canvas.drawBitmap(redBitmap, placeholderRect, destRect, null);
//                        Log.d("row", String.valueOf(row));
//                        Log.d("column", String.valueOf(col));
        }
    }
}
