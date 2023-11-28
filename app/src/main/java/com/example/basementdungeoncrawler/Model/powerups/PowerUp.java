package com.example.basementdungeoncrawler.Model.powerups;

import android.graphics.Canvas;

public interface PowerUp {
    double getX();
    double getY();
    void draw(Canvas canvas);
}
