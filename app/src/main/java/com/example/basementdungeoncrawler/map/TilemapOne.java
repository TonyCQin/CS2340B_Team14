package com.example.basementdungeoncrawler.map;


import static com.example.basementdungeoncrawler.map.MapOneLayout.Number_Of_Column_Tiles;
import static com.example.basementdungeoncrawler.map.MapOneLayout.Number_Of_Row_Tiles;

public class TilemapOne {
    private final MapOneLayout mapLayout;
    private Tile[][] tileMap;

    public TilemapOne() {
        mapLayout = new MapOneLayout();
        initializeTileMap();
    }

    private void initializeTileMap() {
        int[][] layout = mapLayout.getMap1Layout();
        for (int iRow = 0; iRow < Number_Of_Row_Tiles; iRow++) {
            for (int iCol = 0; iCol < Number_Of_Column_Tiles; iCol++) {
                tileMap[iRow][iCol] = Tile.getTile();
                
            }
        }
    }
}
