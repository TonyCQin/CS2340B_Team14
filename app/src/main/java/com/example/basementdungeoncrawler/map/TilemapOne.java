package com.example.basementdungeoncrawler.map;


import static com.example.basementdungeoncrawler.map.MapOneLayout.Number_Of_Column_Tiles;
import static com.example.basementdungeoncrawler.map.MapOneLayout.Number_Of_Row_Tiles;
import static com.example.basementdungeoncrawler.map.MapOneLayout.Tile_Height_Pixels;
import static com.example.basementdungeoncrawler.map.MapOneLayout.Tile_Width_Pixels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.graphics.TileSet;

public class TilemapOne {
    private final MapOneLayout mapLayout;
    private Tile[][] tileMap;
    private TileSet tileMapX;
    private Bitmap mapBitmap;


    public TilemapOne(TileSet tileMapX) {
        mapLayout = new MapOneLayout();
        this.tileMapX = tileMapX;
        initializeTileMap();
    }

    private void initializeTileMap() {
        int[][] layout = mapLayout.getMap1Layout();
        tileMap = new Tile[Number_Of_Row_Tiles][Number_Of_Column_Tiles];
        for (int iRow = 0; iRow < Number_Of_Row_Tiles; iRow++) {
            for (int iCol = 0; iCol < Number_Of_Column_Tiles; iCol++) {
                tileMap[iRow][iCol] = Tile.getTile(layout[iRow][iCol],
                        tileMapX,
                        getRectByIndex(iRow, iCol));
                
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                Number_Of_Column_Tiles * Tile_Width_Pixels,
                Number_Of_Row_Tiles * Tile_Height_Pixels,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < Number_Of_Row_Tiles; iRow++) {
            for (int iCol = 0; iCol < Number_Of_Column_Tiles; iCol++) {
                tileMap[iRow][iCol].draw(mapCanvas);
            }
        }

    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol*Tile_Width_Pixels,
                idxRow*Tile_Height_Pixels,
                (idxCol + 1) * Tile_Width_Pixels,
                (idxCol + 1) * Tile_Height_Pixels
        );
    }
}
