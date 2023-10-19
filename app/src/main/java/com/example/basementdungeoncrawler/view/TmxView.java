package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.content.res.Resources;

import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.graphics.TileSet;
import com.example.basementdungeoncrawler.graphics.TmxLayer;
import com.example.basementdungeoncrawler.graphics.TmxParser;


import java.util.ArrayList;
import java.util.List;

public class TmxView extends View {

    private List<TmxLayer> layers;

    public TmxView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        TileSet props = new TileSet(BitmapFactory.decodeResource(getResources(),
                R.drawable.props), 16, 16);
        TileSet dungeon = new TileSet(BitmapFactory.decodeResource(getResources(),
                R.drawable.tiles2), 16, 16);
        ArrayList<TmxLayer> layers = TmxParser.parseTmxFile(getResources(), R.raw.new_map1);
        int height = 24;
        int width = 12;
        for (int row = 0; row < height; row++) {
            ArrayList<Integer> tileIds = parseTileIds(layers.get(row).getTileData());
            for (int col = 0; col < width; col++) {
                int tileId = tileIds.get(col);
                if (tileId >= 626) {
                    Bitmap propTile = props.getTile(tileId);
                    int x = col * 16;
                    int y = row * 16;
                    canvas.drawBitmap(propTile, x, y, null);
                } else {
                    Bitmap dungeonTile = dungeon.getTile(tileId);
                    int x = col * 16;
                    int y = row * 16;
                    canvas.drawBitmap(dungeonTile, x, y, null);
                }
            }
        }
    }

    private ArrayList<Integer> parseTileIds(String tileIdsString) {
        ArrayList<Integer> tileIds = new ArrayList<>();

        // Split the String into individual tile ID Strings
        String[] tileIdStrings = tileIdsString.trim().split(",");

        // Parse each tile ID String into an integer and add to the list
        for (String tileIdString : tileIdStrings) {
            try {
                int tileId = Integer.parseInt(tileIdString.trim());
                tileIds.add(tileId);
            } catch (NumberFormatException e) {
                // Handle parsing errors if needed
                e.printStackTrace();  // This prints the error to the console for demonstration
            }
        }

        return tileIds;
    }
}
