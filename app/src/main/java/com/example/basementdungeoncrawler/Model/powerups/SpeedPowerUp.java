package com.example.basementdungeoncrawler.Model.powerups;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.basementdungeoncrawler.Model.PlayerSubscriber;
import com.example.basementdungeoncrawler.R;

public class SpeedPowerUp implements PowerUp, PlayerSubscriber {
    private double x;
    private double y;
    private double speed;
    private int sprite;
    private PowerUpNotifier notifier;
    private Context context;
    private boolean claimed;

    public SpeedPowerUp(int x, int y, double speed, PowerUpNotifier notifier, Context context) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        sprite = R.drawable.green_potion;
        this.notifier = notifier;
        this.context = context;
        claimed = false;

    }

    public void draw(Canvas canvas) {
        Bitmap spriteBitmap = BitmapFactory.decodeResource(context.getResources(), sprite);
        canvas.drawBitmap(scaleBitmap(spriteBitmap), (float) x, (float) y, null);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    @Override
    public void update(double positionX, double positionY) {
        if (checkCollision(positionX, positionY)) {
            notifier.apply(this);
            Log.d("powerup", "speed");
            claimed = true;
        }
    }

    public boolean checkCollision(double positionX, double positionY) {
        return (x - 64 < positionX && positionX < x + 64)
                && (y - 128 < positionY && positionY < y + 32);
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double newX) {
        this.x = newX;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public int getSprite() {
        return sprite;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public boolean getClaimed() {
        return claimed;
    }

    public Bitmap scaleBitmap(Bitmap originalBitmap) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();

        double scaleWidth = 2;
        double scaleHeight = 2;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,
            (int) (originalWidth * scaleWidth), (int) (originalHeight * scaleHeight), true);
        return scaledBitmap;
    }
}
