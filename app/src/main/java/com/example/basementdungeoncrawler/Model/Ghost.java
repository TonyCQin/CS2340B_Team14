package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;

public class Ghost extends Enemy{
    private int speed = 60;
    private int damage = 30;
    private int HP = 5;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private Movement movement;
    private char direction;
    private PlayerData player;

    public Ghost(Context context, double positionX, double positionY, int HP, int damage,
                    int radius, int speed) {

        super(context, positionX, positionY, HP, damage, 100, speed, new Paint());

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public void move() {
        this.incrementPace();
        if (HP > 0) {
            if (this.getPace() % 5 == 0) {
                direction = this.getRandomDirection();
            }
//            if (collision.getCollideWithPlayer() == true) {
//                player.setHp(player.getHp() - damage);
//            }
//            Log.d("Health", String.valueOf(player.getHp()));
            switch (direction) {
            case 'w':
                if (!collision.getUp()) {
                    this.setPositionY(this.getPositionY() - this.speed);
                }
                break;
            case 'a':
                if (!collision.getLeft()) {
                    this.setPositionX(this.getPositionX() - this.speed);
                }
                break;
            case 's':
                if (!collision.getBottom()) {
                    this.setPositionY(this.getPositionY() + this.speed);
                }
                break;
            case 'd':
                if (!collision.getRight()) {
                    this.setPositionX(this.getPositionX() + this.speed);
                }
                break;
            default:
                break;
            }
        }
        notifySubscribers();
    }

    public void draw(Canvas canvas) {

        super.draw(canvas, paint);
    }
}
