package com.example.basementdungeoncrawler.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class MapRenderer {
    private TileSet tileSet;
    private List<TmxLayer> layers;

    public MapRenderer(TileSet tileSet, List<TmxLayer> layers) {
        this.tileSet = tileSet;
        this.layers = layers;
    }

    public void renderMap(Canvas canvas, String tileIdsString, int tileSize) {
        for (TmxLayer layer : layers) {
            List<Integer> tileIds = parseTileIds(layer.getTileData());
            for (int tileId : tileIds) {
                Tile tile = new Tile(tileId, tileSet.getTile(tileId));

            }
        }
    }

    private List<Integer> parseTileIds(String tileIdsString) {
        List<Integer> tileIds = new ArrayList<>();

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
