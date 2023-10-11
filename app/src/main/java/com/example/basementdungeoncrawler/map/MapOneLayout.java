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
        //0 - wall
        //1 - floor
        //2 - empty
        map1Layout = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 6, 6, 6, 0, 0, 6, 6, 6, 0},
                {0, 6, 6, 6, 0, 0, 6, 6, 6, 0},
                {0, 6, 6, 6, 6, 6, 6, 6, 6, 0},
                {0, 6, 6, 6, 6, 6, 6, 6, 6, 0},
                {0, 0, 0, 0, 0, 0, 6, 6, 6, 0},
                {2, 2, 2, 0, 0, 0, 6, 6, 6, 0},
                {2, 2, 2, 0, 6, 6, 6, 6, 6, 6},
                {2, 2, 2, 0, 6, 6, 6, 6, 6, 6},
                {2, 2, 2, 0, 0, 0, 0, 0, 0, 0}
        };
    }
}