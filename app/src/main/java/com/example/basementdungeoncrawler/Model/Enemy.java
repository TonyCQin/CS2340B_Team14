package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Enemy{
    //implements PlayerSubscriber
    private int HP;
    private int damage;
    private int radius;
    private int speed;
    private Paint paint;
    private Movement movement;
    private char direction;
    private double xPosition;

    private double yPosition;
    private static int pace;

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
        int random = (int) (Math.random() * 4) + 1;
        char direction = ' ';
        switch (random) {
        case 0:
            direction = 'w';
            break;
        case 1:
            direction = 'a';
            break;
        case 2:
            direction = 's';
            break;
        case 3:
            direction = 'd';
            break;
        default:
            break;
        }
        return direction;
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

    public int getPace() {
        return pace;
    }

    public void incrementPace() {
        pace++;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }

    public void move(int speed) {
        this.incrementPace();
        if (HP > 0) {
            if (this.getPace() % 3 == 0) {
                direction = this.getRandomDirection();
            }
            switch (direction) {
                case 'w':
                    //if (!collision.getUp()) {
                    this.setPositionY(this.getPositionY() - this.speed);
                    //}
                    break;
                case 'a':
                    //if (!collision.getLeft()) {
                    this.setPositionX(this.getPositionX() - this.speed);
                    //}
                    break;
                case 's':
                    //if (!collision.getBottom()) {
                    this.setPositionY(this.getPositionY() + this.speed);
                    //}
                    break;
                case 'd':
                    //if (!collision.getRight()) {
                    this.setPositionX(this.getPositionX() + this.speed);
                    //}
                    break;
                default:
                    break;
            }
        }
    }
}
