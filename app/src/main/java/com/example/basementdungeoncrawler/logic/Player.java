package com.example.basementdungeoncrawler.logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class Player {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX,(float) positionY, (float) radius, paint);
    }

    public void update() {

    }
}
