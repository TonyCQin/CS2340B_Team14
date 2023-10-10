package com.example.basementdungeoncrawler.map;

public class MapOneLayout {
    public static final int Tile_Width_Pixels = 16;
    public static final int Tile_Height_Pixels = 16;
    public static final int Number_Of_Row_Tiles = 10;
    public static final int Number_Of_Column_Tiles = 10;

    private int[][] map1Layout;

    public MapOneLayout() {
        initializeLayout();
    }

    public int[][] getMap1Layout() {
        return map1Layout;
    }
    private void initializeLayout() {
        map1Layout = new int[][] {
                {0, 1, 1, 2, 25, 10, 4, 2, 3, 5},
                {10, 6, 6, 6, 15, 20, 6, 6, 6, 5},
                {20, 6, 6, 6, 2, 3, 6, 6, 6, 15},
                {20, 6, 6, 6, 6, 6, 6, 6, 6, 25},
                {30, 6, 6, 6, 6, 6, 6, 6, 6, 15},
                {40, 41, 41, 42, 44, 53, 6, 6, 6, 35},
                {78, 78, 78, 20, 3, 4, 6, 6, 6, 15},
                {78, 78, 78, 30, 6, 6, 6, 6, 6, 6},
                {78, 78, 78, 10, 6, 6, 6, 6, 6, 6},
                {78, 78, 78, 40, 42, 44, 44, 43, 43, 42}
        };
    }
}