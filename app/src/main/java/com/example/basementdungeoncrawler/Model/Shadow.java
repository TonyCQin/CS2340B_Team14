package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class Shadow extends Enemy {
    private int speed = 48;
    private int damage = 10;
    private int HP = 20;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    public Shadow(Context context, double positionX, double positionY, int HP, int damage,
                  int radius, int speed) {
        super(context, positionX, positionY, HP, damage, 100, speed, new Paint());

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.paint = paint;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }
}
