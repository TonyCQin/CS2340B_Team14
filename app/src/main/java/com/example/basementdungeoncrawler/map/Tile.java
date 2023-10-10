package com.example.basementdungeoncrawler.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.basementdungeoncrawler.graphics.TileSet;

abstract public class Tile {

    private final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        LEFT_WALL_TOP, TOP_WALL_1, TOP_WALL_2, TOP_WALL_3, TOP_WALL_4, RIGHT_WALL_TOP, F1, //F2, F3, F4,
        LEFT_WALL_1, /*B1, B2, B3, B4, */RIGHT_WALL_1, //F5, F6, F7, F8,
        LEFT_WALL_2, /*B5, B6, B7, B8, */RIGHT_WALL_2, //F9, F10, F11, F12,
        LEFT_WALL_3, /* B9, B10, B11, B12, */RIGHT_WALL_3, //DOOR1_1, DOOR1_2, LATCH, LADDER,
        LEFT_WALL_BOT, BOT_WALL_1, BOT_WALL_2, BOT_WALL_3, BOT_WALL_4, RIGHT_WALL_BOT, //GATE_M1, GATE_L1, GATE_R1, CHEST_DL,
        EX_WALL_0, EX_WALL_1, EX_WALL_2, EX_WALL_3, EX_WALL_4, EX_WALL_5, //GATE_M2, GATE_L2, GATE_R2, CHEST_DS,
        //F13, F14, F15, F16, BONE_WING, BONE_HOLDER, GATE2_1, GATE2_2, BONES, EX_F1,
        /*F17, F18, F19, F20, BANNER, GLASS_1, GLASS_2, SKULL_BONES, */ EMPTY, //EX_F2,
        //CHEST_SG, CHEST_SS, CHEST_LS, CHEST_LG, CHEST2_LS, CHEST2_SS, COIN, S_MANA, SILVER_Key, L_HEALTH,
        //M_TORCH, S_TORCH, NO_F_TORCH, CANDLE_1, CANDLE_1_NO_F, CANDLE_2, CANDLE_2_NO_F, L_MANA, S_HEALTH, GOLD_KEY,
    }

    public static Tile getTile(int idxTileType, TileSet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
            case LEFT_WALL_TOP:
                return new LeftWallTop(spriteSheet, mapLocationRect);
            case LEFT_WALL_TOP:
                return new LeftWallTop(spriteSheet, mapLocationRect);
            case LEFT_WALL_TOP:
                return new LeftWallTop(spriteSheet, mapLocationRect);
            default: return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
