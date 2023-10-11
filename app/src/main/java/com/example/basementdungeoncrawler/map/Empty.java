package com.example.basementdungeoncrawler.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.graphics.TileS;
import com.example.basementdungeoncrawler.graphics.TileSet;

public class Empty extends Tile {
    private final TileS tile;

    public Empty(TileSet tileSet, Rect mapLocationRect) {
        super(mapLocationRect);
        tile = tileSet.getEmpty();
    }

    @Override
    public void draw(Canvas canvas) {
        tile.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
