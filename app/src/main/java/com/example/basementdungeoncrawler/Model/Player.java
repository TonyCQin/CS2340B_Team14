package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;


public class Player {

    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private Movement movement;
    private ArrayList<PlayerSubscriber> subscribers;
    private Context context;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.context = context;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        int sprite = PlayerData.getPlayer().getSpriteSelected();
        Log.d("sprite", String.valueOf(sprite));
        Bitmap spriteBitmap = BitmapFactory.decodeResource(context.getResources(), sprite, null);

        int newWidth = (int) spriteBitmap.getWidth() * 2;
        int newHeight = (int) spriteBitmap.getHeight() * 2;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(spriteBitmap, newWidth, newHeight, true);
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
        canvas.drawBitmap(scaledBitmap, (float) positionX, (float) positionY, null);
    }

    public void move(char direction, Collision collision) {
        movement.walk(direction);
        movement.run(direction);
        notifySubscribers();
    }

    public void subscribe(PlayerSubscriber sub) {
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
        subscribers.add(sub);
    }


    protected void notifySubscribers() {
        for (PlayerSubscriber sub : subscribers) {
            sub.update(positionX, positionY, radius);
        }
    }

    public void removeObserver(PlayerSubscriber sub) {
        subscribers.remove(sub);
    }

    public void setMovement(Movement m) {
        movement = m;
    }

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public double getRadius() {
        return radius;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
