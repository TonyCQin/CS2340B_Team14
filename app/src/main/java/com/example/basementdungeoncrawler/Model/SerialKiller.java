package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class SerialKiller extends Enemy {
    private int speed = 50;
    private int damage = 50;
    private int HP = 100;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    public SerialKiller(Context context, double positionX, double positionY, int HP, int damage,
                  int radius, int speed) {
        super(context, positionX, positionY, HP, damage, 100, speed, new Paint());

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }
}

