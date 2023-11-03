package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Paint;

public abstract class Enemy {
    private double positionX;
    private double positionY;
    private int HP;
    private int damage;
    private int radius;
    private int speed;
    private Paint paint;

    public Enemy(Context context, double positionX, double positionY, int HP, int damage,
                 int radius, int speed, Paint paint) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.HP = HP;
        this.damage = damage;
        this.radius = radius;
        this.paint = paint;
    }

    public char getRandomDirection() {
        int random = (int) (Math.random() * 4);
        char direction = ' ';
        switch (random) {
        case 0:
            direction = 'w';
        case 1:
            direction = 'a';
        case 2:
            direction = 's';
        case 3:
            direction = 'd';
        default:
            break;
        }
        return direction;
    }


}
