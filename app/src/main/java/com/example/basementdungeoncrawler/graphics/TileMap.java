package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;

import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;

public class TileMap {
    private Tile[][] tileMap;
    private ArrayList<Tile[][]> layers;
    private Context context;

    public TileMap(Context context, int resourceID) {
        this.context = context;
        MapLayout mapLayout = new MapLayout(context, resourceID);
        layers = new ArrayList<>();
        initializeTileMap(context, mapLayout);
    }

    /**
     * Takes the mapLayout and generates and List of Tile[][] layers for use in MapView.java
     * @param context context to get resources from
     * @param mapLayout mapLayout that stored the tileIds from the parsed map
     */
    private void initializeTileMap(Context context, MapLayout mapLayout) {
//        Log.d("tile map initialized", "initialized");
        tileMap = new Tile[24][12];
        ArrayList<int[][]> tileMapLayers = mapLayout.getTileMapLayers();
//        Log.d("tileMapLayers", String.valueOf(tileMapLayers));
//        Log.d("layers size", String.valueOf(layers.size()));
        for (int layer = 0; layer < tileMapLayers.size(); layer++) {
            int[][] layout = tileMapLayers.get(layer);
//            Log.d("layout", String.valueOf(layout));
            for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
                for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                    TileSet dungeonTileSet = new TileSet(context, R.drawable.tiles2, 16);
                    TileSet propTileSet = new TileSet(context, R.drawable.props, 16);
                    int tileId = layout[row][col];
//                    Log.d("tileID", String.valueOf(tileId));
                    tileMap[row][col] = dungeonTileSet.getTile(context, tileId);
                }
            }
            this.layers.add(tileMap);
        }
    }

    /**
     * getter for layers
     * @return layers
     */
    public ArrayList<Tile[][]> getLayers() {
        return layers;
    }

    public Tile getTile(double positionX, double positionY) {
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int tileWidth = screenWidth / 12;
        int tileHeight = screenHeight / 24;
        int col = ((int) positionX) / tileWidth;
        int row = ((int) positionY) / tileHeight;
        Log.d("col", String.valueOf(col));
        Log.d("row", String.valueOf(row));
        try {
            return tileMap[row][col];
        } catch (Exception error) {
            return new Tile(1, new Rect(0,0,16,16));
        }

    }
}
