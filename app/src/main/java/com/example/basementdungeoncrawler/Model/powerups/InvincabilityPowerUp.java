package com.example.basementdungeoncrawler.Model.powerups;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.basementdungeoncrawler.Model.PlayerSubscriber;
import com.example.basementdungeoncrawler.R;

public class InvincabilityPowerUp implements PowerUp, PlayerSubscriber {
    private double x;
    private double y;
    private int HPIncrease;
    private int sprite;
    private PowerUpNotifier notifier;
    private Context context;
    private boolean claimed;

    public InvincabilityPowerUp(int x, int y, PowerUpNotifier notifier, Context context) {
        this.x = x;
        this.y = y;
        sprite = R.drawable.blue_potion;
        this.notifier = notifier;
        this.context = context;
        claimed = false;
    }

    public void draw(Canvas canvas) {
        Bitmap spriteBitmap = BitmapFactory.decodeResource(context.getResources(), sprite);
        canvas.drawBitmap(scaleBitmap(spriteBitmap), (float) x, (float) y, null);
    }

    @Override
    public void update(double positionX, double positionY) {
        if (checkCollision(positionX, positionY)) {
            Log.d("powerup", "inv");
            notifier.apply(this);
            claimed = true;
        }
    }

    public boolean checkCollision(double positionX, double positionY) {
//        Log.d("X", String.valueOf(x));
//        Log.d("Y", String.valueOf(y));
//        Log.d("player X", String.valueOf(positionX));
//        Log.d("player Y", String.valueOf(positionY));
        return (x - 64 < positionX && positionX < x + 64)
                && (y - 64 < positionY && positionY < y + 64);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, (int) (originalWidth * scaleWidth), (int) (originalHeight * scaleHeight), true);
        return scaledBitmap;
    }
}
