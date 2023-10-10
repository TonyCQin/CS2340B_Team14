package com.example.basementdungeoncrawler.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.graphics.TileS;
import com.example.basementdungeoncrawler.graphics.TileSet;

public class LeftWallTop extends Tile {
    private final TileS tile;

    public LeftWallTop(TileSet tileSet, Rect mapLocationRect) {
        super(mapLocationRect);
        tile = tileSet.getLeftWallTop();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
