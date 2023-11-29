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
    /**
     * General super constructor for all enemies
     * @param context context needed for sprite drawing
     * @param positionX initial x position
     * @param positionY initial y position
     * @param hp enemy health points
     * @param radius radius of enemy sprite
     * @param speed enemy unique speed for movement
     */
    public Enemy(Context context, double positionX, double positionY, int hp,
                 int radius, int speed) {
        this.xPosition = positionX;
        this.yPosition = positionY;
        this.hp = hp;
        this.radius = radius;
    }

    /**
     * draw method for sprite rendering
     * @param canvas Canvas where sprite is being drawn
     */
    public abstract void draw(Canvas canvas);
    /**
     * movement method all enemies will implement
     */
    public abstract void move();
    /**
     * damage to player method all enemies will implement
     */
    public abstract void damagePlayer();
    /**
     * method of each enemy dying
     * @param context context needed to show dying sprite rendering
     */

    public abstract void die(Context context);
    /**
     * method that generates random direction for unique movement implementaiton of each enemy
     * @return character representing a direction
     */
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
            Log.d("stuff getting sent", String.format("x: %f, y: %f, r: %d, s: %d", x,
                    y, radius, speed));
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
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, (int)
                (originalWidth * scaleWidth), (int) (originalHeight * scaleHeight), true);
        return scaledBitmap;
    }
}
