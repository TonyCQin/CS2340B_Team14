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
    private boolean reachGoal;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        reachGoal = false;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }

    public void move(char direction) {
        switch(direction) {
            case 'w':
                positionY = positionY - 16;
                break;
            case 'a':
                positionX = positionX - 16;
                break;
            case 's':
                positionY = positionY + 16;
                break;
            case 'd':
                positionX = positionX + 16;
                break;
        }
    }
    public void setReachGoal(boolean x) {
        reachGoal = x;
    }
    public double getPositionX(){
        return positionX;
    }

    public double getPositionY(){
        return positionY;
    }

}
