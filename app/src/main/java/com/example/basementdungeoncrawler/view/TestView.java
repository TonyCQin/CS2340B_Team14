package com.example.basementdungeoncrawler.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.TileSet;

public class TestView extends View {
    private Rect srcRect;
    private TileSet dungeonTileSet;
    private TileSet propTileSet;
    private Rect destRect;
    public TestView(Context context, Rect rect) {
        super(context);
        this.srcRect = rect;
        dungeonTileSet = new TileSet(context, R.drawable.tiles2, 16);
        propTileSet = new TileSet(context, R.drawable.props, 16);
        this.destRect = new Rect(0, 0, 1000, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("srcRect", String.valueOf(srcRect));
        Log.d("destRect", String.valueOf(destRect));
        canvas.drawBitmap(dungeonTileSet.getBitmap(), srcRect, destRect, null);
        canvas.drawBitmap(propTileSet.getBitmap(), srcRect, destRect, null);
    }
}
