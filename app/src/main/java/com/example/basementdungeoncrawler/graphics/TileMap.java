package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.util.Log;

import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import com.example.basementdungeoncrawler.R;

public class TileMap {
    private final MapLayout mapLayout;
    private Tile[][] tileMap;

    public TileMap(Context context) {
        mapLayout = new MapLayout(context);
        initializeTileMap(context);
    }

    private void initializeTileMap(Context context) {
        tileMap = new Tile[24][12];
        int[][] layout = mapLayout.getLayout();
        for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                TileSet dungeonTileSet = new TileSet(context, R.drawable.tiles2, 16);
                TileSet propTileSet = new TileSet(context, R.drawable.props, 16);
                int tileId = layout[row][col];
//                Log.d("tileID", String.valueOf(tileId));

//                Log.d("placeholder tile", String.valueOf(placeholder.getTileId()));
                tileMap[row][col] = dungeonTileSet.getTile(tileId);
            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}
