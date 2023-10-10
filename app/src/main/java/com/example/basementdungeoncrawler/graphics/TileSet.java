package com.example.basementdungeoncrawler.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.R;

public class TileSet {
    private static int Sprite_Width_Pixels = 16;
    private static int Sprite_Height_Pixels = 16;
    private Bitmap bitmap;

    public TileSet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.Dungeon_Tileset, bitmapOptions);
    }

    public TileS getLeftWallTop() {
        return getTileByIndex(0, 0);
    }


    private TileS getTileByIndex(int idxRow, int idxCol) {
        return new TileS(this, new Rect(
                idxCol*Sprite_Width_Pixels,
                idxRow*Sprite_Height_Pixels,
                (idxCol + 1) * Sprite_Width_Pixels,
                (idxCol + 1) * Sprite_Height_Pixels

        ));
    }

    public TileS getWantedTile() {
        return new TileS(this, new Rect(0, 0, 16, 16))
    }

    public Bitmap getBitMap() {
        return bitmap;
    }
}
