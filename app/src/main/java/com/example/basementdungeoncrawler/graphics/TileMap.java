package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.util.Log;

import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;

public class TileMap {
    private final MapLayout mapLayout;
    private Tile[][] tileMap;
    private ArrayList<Tile[][]> layers;

    public TileMap(Context context, int resourceID) {
        mapLayout = new MapLayout(context, resourceID);
        layers = new ArrayList<>();
        initializeTileMap(context, mapLayout, layers);
    }

    private void initializeTileMap(Context context, MapLayout mapLayout, ArrayList<Tile[][]> layers) {
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
                    tileMap[row][col] = dungeonTileSet.getTile(tileId);
                }
            }
            layers.add(tileMap);
        }

    }

    public ArrayList<Tile[][]> getLayers() {
        return layers;
    }


}
