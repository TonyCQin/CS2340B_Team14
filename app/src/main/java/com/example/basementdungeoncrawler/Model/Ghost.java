package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;


public class Ghost extends Enemy {
    private int speed = 60;
    private int damage = 10;
    private int hp = 5;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private Movement movement;
    private char direction;
    private PlayerData player;
    private Game game;
    public Ghost(Context context, double positionX, double positionY, int hp, int damage,
                 int radius, int speed) {

        super(context, positionX, positionY, hp, radius, speed, new Paint());
        super.setDamage(damage);

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        this.player = PlayerData.getPlayer();
        this.game = Game.getGame();
        this.damage = 10;
    }

    public void move() {
        this.incrementPace();
        if (hp > 0) {
            if (this.getPace() % 5 == 0) {
                direction = this.getRandomDirection();
            }
            if (collision.getCollideWithPlayer()) {
                damagePlayer();
            }
            switch (direction) {
            case 'w':
                if (!collision.getUp()) {
                    this.setPositionY(this.getPositionY() - this.speed);
                }

                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 'a':
                if (!collision.getLeft()) {
                    this.setPositionX(this.getPositionX() - this.speed);
                }

                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 's':
                if (!collision.getBottom()) {
                    this.setPositionY(this.getPositionY() + this.speed);
                }

                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
                }
                break;
            case 'd':
                if (!collision.getRight()) {
                    this.setPositionX(this.getPositionX() + this.speed);
                }

                if (collision.getCollideWithPlayer()) {
                    damagePlayer();
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

    public void damagePlayer() {
        int finalDamage = damage * game.getDifficulty();
        int newHP = player.getHp() - finalDamage;
        player.setHp(newHP);
        Log.d("", String.valueOf(player.getHp()));
    }
}
