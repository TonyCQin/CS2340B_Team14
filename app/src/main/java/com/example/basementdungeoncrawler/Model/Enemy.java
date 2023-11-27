package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public abstract class Enemy {
    private int hp;
    private int damage;
    private int radius;
    private int speed;
    private Paint paint;
    private Movement movement;
    private char direction;
    private double xPosition;

    private double yPosition;
    private static int pace;
    private ArrayList<EnemySubscriber> subscribers;

    protected EnemyCollision collision;

    public Enemy(Context context, double positionX, double positionY, int hp,
                 int radius, int speed) {
        this.xPosition = positionX;
        this.yPosition = positionY;
        this.hp = hp;
        this.radius = radius;
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
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int newHp) {
        this.hp = newHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }

    public void setPositionX(double x) {
        xPosition = x;
    }

    public void setPositionY(double y) {
        yPosition = y;
    }

    public double getPositionX() {
        return xPosition;
    }

    public double getPositionY() {
        return yPosition;
    }

    public int getPace() {
        return pace;
    }

    public void incrementPace() {
        pace++;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }

    public void subscribe(EnemySubscriber sub) {
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
        subscribers.add(sub);
    }

    public void removeObserver(EnemySubscriber sub) {
        subscribers.remove(sub);
    }

    protected void notifySubscribers(double x, double y, int radius, int speed) {
        for (EnemySubscriber sub : subscribers) {
            Log.d("updating subscibers", "");
            Log.d("stuff getting sent", String.format("x: %f, y: %f, r: %d, s: %d", x, y,
                    radius, speed));
            sub.updateEnemyPosition(x, y, radius, speed);
        }
    }

    public void setCollision(EnemyCollision collision) {
        this.collision = collision;
    }

    public Bitmap scaleBitmap(Bitmap originalBitmap) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();

        double scaleWidth = radius / 32;
        double scaleHeight = radius / 32;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,
                (int) (originalWidth * scaleWidth), (int) (originalHeight * scaleHeight), true);
        return scaledBitmap;
    }
}
