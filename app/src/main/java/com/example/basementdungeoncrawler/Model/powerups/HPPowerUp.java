package com.example.basementdungeoncrawler.Model.powerups;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.basementdungeoncrawler.Model.PlayerSubscriber;
import com.example.basementdungeoncrawler.R;

public class HPPowerUp implements PowerUp, PlayerSubscriber {
    private double x;
    private double y;
    private int hpIncrease;
    private int sprite;
    private PowerUpNotifier notifier;
    private Context context;
    private boolean claimed;

    public HPPowerUp(int x, int y, int hpIncrease, PowerUpNotifier notifier, Context context) {
        this.x = x;
        this.y = y;
        this.hpIncrease = hpIncrease;
        sprite = R.drawable.red_potion;
        this.notifier = notifier;
        this.context = context;
        claimed = false;
    }

    public void draw(Canvas canvas) {
        Bitmap spriteBitmap = BitmapFactory.decodeResource(context.getResources(), sprite);
        canvas.drawBitmap(scaleBitmap(spriteBitmap), (float) x, (float) y, null);
    }

    public int getHPIncrease() {
        return hpIncrease;
    }

    public void setHPIncrease(int newHPIncrease) {
        hpIncrease = newHPIncrease;
    }

    @Override
    public void update(double positionX, double positionY) {
        if (checkCollision(positionX, positionY)) {
            Log.d("powerup", "hp");
            notifier.apply(this);
            claimed = true;
        }
    }

    public boolean checkCollision(double positionX, double positionY) {
        Log.d("X", String.valueOf(x));
        Log.d("Y", String.valueOf(y));
        Log.d("player X", String.valueOf(positionX));
        Log.d("player Y", String.valueOf(positionY));
        return (x - 64 < positionX && positionX < x + 64)
                && (y - 128 < positionY && positionY < y + 32);
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

    public boolean getClaimed() {
        return claimed;
    }
    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
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
