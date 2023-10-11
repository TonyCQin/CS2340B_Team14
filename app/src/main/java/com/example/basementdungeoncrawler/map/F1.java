package com.example.basementdungeoncrawler.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.graphics.TileS;
import com.example.basementdungeoncrawler.graphics.TileSet;

public class F1 extends Tile {
    private final TileS tile;

    public F1(TileSet tileSet, Rect mapLocationRect) {
        super(mapLocationRect);
        tile = tileSet.getF1();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
