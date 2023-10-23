package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
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
     * @param resourceId takes in the resourceID of an xml map file and generates the layers
     *                   of the map
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
    private void initializeLayout(Context context, int resourceId,
                                  ArrayList<int[][]> tileMapLayers) {
        TmxParser parser = new TmxParser(context);
        List<List<Integer>> layers = parser.parseTmxFile(resourceId);

        for (int layer = 0; layer < layers.size(); layer++) {
            int[][] newLayout = new int[24][12];
            List<Integer> currentLayer = layers.get(layer);
            for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
                for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                    int tileId = layers.get(layer).get((row * 12) + col);
                    newLayout[row][col] = tileId;

                }
            }
            tileMapLayers.add(newLayout);
        }
    }

    /**
     * getter of tileMapLayers
     * @return tileMapLayers;
     */
    public ArrayList<int[][]> getTileMapLayers() {
        return tileMapLayers;
    }
}
