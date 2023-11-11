package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Paint;

public abstract class Enemy implements PlayerSubscriber{
    private int HP;
    private int damage;
    private int radius;
    private int speed;
    private Paint paint;
    private Movement movement;

    private double xPosition;

    private double yPosition;

    public Enemy(Context context, double positionX, double positionY, int HP, int damage,
                 int radius, int speed, Paint paint) {
        this.xPosition = positionX;
        this.yPosition = positionY;
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

    public void move() {
        movement.enemyMove(this.speed);
    }

    public int getSpeed() {
        return speed;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public void setPositionX(double x) { xPosition = x; }

    public void setPositionY(double y) { yPosition = y; }

    public double getPositionX() { return xPosition; }

    public double getPositionY() { return yPosition; }
    @Override
    public void update(double positionX, double positionY, double radius) {
        movement.enemyMove(speed);
    }
}
