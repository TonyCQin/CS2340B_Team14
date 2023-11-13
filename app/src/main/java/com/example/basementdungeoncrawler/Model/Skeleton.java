package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class Skeleton extends Enemy {
    private int speed = 10;
    private int damage = 15;
    private int hp = 20;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private Movement movement;
    private char direction;

    public Skeleton(Context context, double positionX, double positionY, int hp, int damage,
                    int radius, int speed) {
        super(context, positionX, positionY, hp, 100, speed, new Paint());
        super.setDamage(damage);

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.grey);
        paint.setColor(color);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public void move() {
        this.incrementPace();
        if (hp > 0) {
            if (this.getPace() % 7 == 0) {
                direction = this.getRandomDirection();
            }
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
            notifySubscribers();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas, paint);
    }
}
