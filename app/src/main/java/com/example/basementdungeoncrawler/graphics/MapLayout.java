package com.example.basementdungeoncrawler.graphics;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;
import android.util.Log;

import com.example.basementdungeoncrawler.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 16;
    public static final int TILE_HEIGHT_PIXELS = 16;
    public static final int NUMBER_OF_ROW_TILES = 24;
    public static final int NUMBER_OF_COLUMN_TILES = 12;

    private int[][] layout;
    private ArrayList<int[][]> tileMapLayers;

    /**
     *
     * @param context context to get resources from the project
     * @param resourceId takes in the resourceID of an xml map file and generates the layers of the map
     */
    public MapLayout(Context context, int resourceId) {
        tileMapLayers = new ArrayList<>();
        initializeLayout(context, resourceId, tileMapLayers);
    }

    /**
     * Log.d statements provide information for debugigng
     * runs the parser to generate the tileMapLayers
     * @param context context to get resources from
     * @param resourceId resourceID of an XML map file
     * @param tileMapLayers ArrayList to store layers
     */
    private void initializeLayout(Context context, int resourceId, ArrayList<int[][]> tileMapLayers) {
//        generating a parser
        TmxParser parser = new TmxParser(context);
        List<List<Integer>> layers = parser.parseTmxFile(resourceId);

        for (int layer = 0; layer < layers.size(); layer++) {
            int[][] newLayout = new int[24][12];
//            Log.d("Layer size", String.valueOf(tileIds.get(layer).size()));
            List<Integer> currentLayer = layers.get(layer);
            for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
                for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                    int tileId = layers.get(layer).get((row * 12) + col);
//                    Log.d("tileId", String.valueOf(tileId));
//                    Log.d("row, col", String.format("%s, %s", row, col));
                    newLayout[row][col] = tileId;

                }
            }
//            Log.d("layout?", String.valueOf(newLayout));
//            Log.d("entry", String.valueOf(newLayout[0][0]));
//            Log.d("is null", String.valueOf((null == newLayout)));
            tileMapLayers.add(newLayout);
        }
//        Log.d("tileMapLayers", String.valueOf(tileMapLayers));
    }

    /**
     * getter of tileMapLayers
     * @return tileMapLayers;
     */
    public ArrayList<int[][]> getTileMapLayers() {
        return tileMapLayers;
    }
}
