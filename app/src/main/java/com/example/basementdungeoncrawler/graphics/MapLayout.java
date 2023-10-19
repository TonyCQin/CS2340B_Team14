package com.example.basementdungeoncrawler.graphics;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;
import android.util.Log;

import com.example.basementdungeoncrawler.R;

import java.util.List;
public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 16;
    public static final int TILE_HEIGHT_PIXELS = 16;
    public static final int NUMBER_OF_ROW_TILES = 24;
    public static final int NUMBER_OF_COLUMN_TILES = 12;

    private int[][] layout;
    public MapLayout(Context context) {
        initializeLayout(context);
    }

    private void initializeLayout(Context context) {
        TmxParser parser = new TmxParser(context);
        List<List<Integer>> layers = parser.parseTmxFile(R.raw.new_map1);
        layout = new int[24][12];
        for (int layer = 0; layer < layers.size(); layer++) {
//            Log.d("Layer size", String.valueOf(tileIds.get(layer).size()));
            List<Integer> currentLayer = layers.get(layer);
            for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
                for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                    int tileId = layers.get(layer).get((row * 12) + col);
                    Log.d("tileId", String.valueOf(tileId));
                    Log.d("row, col", String.format("%s, %s", row, col));
                    layout[row][col] = tileId;
                }
            }
        }
    }

    public int[][] getLayout() {
        return layout;
    }
}
