package com.example.basementdungeoncrawler.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class TileS {

    private final TileSet tileSet;
    private final Rect rect;

    public TileS(TileSet tileSet, Rect rect) {
        this.tileSet = tileSet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int left, int top) {
        canvas.drawBitmap(
                tileSet.getBitMap(),
                rect,
                rect,
                null
        );
    }
}