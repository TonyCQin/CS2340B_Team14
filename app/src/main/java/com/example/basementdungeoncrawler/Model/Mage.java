package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.basementdungeoncrawler.R;

public class Mage extends Enemy {
    private int speed = 48;
    private int damage = 10;
    private int hp = 20;
    private double positionX;
    private double positionY;
    private int radius;
    private Paint paint;
    private Movement movement;
    private char direction;
    private Bitmap spriteBitmap;

    private Game game;
    private PlayerData player;
    private Context context;

    public Mage(Context context, double positionX, double positionY, int hp, int damage,
                  int radius, int speed) {
        super(context, positionX, positionY, hp, radius, speed, damage);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.context = context;

        this.game = Game.getGame();
        this.player = PlayerData.getPlayer();

        spriteBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mage, null);
    }

    public void move() {
        this.incrementPace();
        if (hp > 0) {
            //            Log.d("mage up", String.valueOf(collision.getUp()));
            //            Log.d("mage down", String.valueOf(collision.getBottom()));
            //            Log.d("mage left", String.valueOf(collision.getLeft()));
            //            Log.d("mage right", String.valueOf(collision.getRight()));
            if (this.getPace() % 3 == 0) {
                direction = this.getRandomDirection();
            }
            if (collision.getCollideWithPlayer()) {
                damagePlayer();
            }
            switch (direction) {
            case 'w':
                if (!collision.getUp()) {
                    positionY -= speed;
                    setPositionY(getPositionY() - speed);
                }
                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 'a':
                if (!collision.getLeft()) {
                    positionX -= speed;
                    setPositionX(getPositionX() - speed);
                }
                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 's':
                if (!collision.getBottom()) {
                    positionY += speed;
                    setPositionY(getPositionY() + speed);
                }
                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 'd':
                if (!collision.getRight()) {
                    positionX += speed;
                    setPositionX(getPositionX() + speed);
                }
                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            default:
                break;
            }
            //            Log.d("mage new location", String.format("%f, %f", positionX, positionY));
            notifySubscribers(positionX, positionY, radius, speed);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(super.scaleBitmap(spriteBitmap), (float) positionX, (float) positionY,
            null);
    }

    public void damagePlayer() {
        int finalDamage = damage * game.getDifficulty();
        int newHP = player.getHp() - finalDamage;
        player.setHp(newHP);
        Log.d("new HP", String.valueOf(player.getHp()));
    }

    public void die(Context context) {
        hp = 0;
        speed = 0;
        game.setScore(game.getScore() + 25);
        spriteBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mage_death,
                null);
    }
}
