package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;


public class Player {

    private double positionX;
    private double positionY;
    private double radius;
    private double attackRange = 200;
    private Paint paint;
    private Movement movement;
    private ArrayList<PlayerSubscriber> subscribers;
    private Context context;

    /**
     * Player constructor
     * @param context where player is being spawned, needed for rendering sprite
     * @param positionX initial X position of player
     * @param positionY initial Y position of player
     */
    public Player(Context context, double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.context = context;

    }

    /**
     * draw method to render sprite of player
     * @param canvas where the player sprite is being drawn
     */
    public void draw(Canvas canvas) {
        int sprite = PlayerData.getPlayer().getSpriteSelected();
        Bitmap spriteBitmap = BitmapFactory.decodeResource(context.getResources(), sprite, null);

        int newWidth = (int) spriteBitmap.getWidth() * 2;
        int newHeight = (int) spriteBitmap.getHeight() * 2;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(spriteBitmap, newWidth, newHeight, true);
        canvas.drawBitmap(scaledBitmap, (float) positionX, (float) positionY, null);
    }

    /**
     * movement method that calls specific movement type
     * @param direction direction player is moving in
     * @param collision collision object that helps check for collisions with walls and enemies
     */
    public void move(char direction, Collision collision) {
        movement.walk(direction);
        movement.run(direction);
        notifySubscribers();
    }

    /**
     * subscribe method for entities that need to be updated with player's current position
     * @param sub entity being subscribed
     */
    public void subscribe(PlayerSubscriber sub) {
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
        subscribers.add(sub);
    }

    /**
     * updating all those subscribed to PlayerSubscriber with the player's current position
     */
    protected void notifySubscribers() {
        for (PlayerSubscriber sub : subscribers) {
            sub.update(positionX, positionY);
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
    public double getAttackRadius() {
        return attackRange;
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
    public void setAttackRadius(double attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * method with logic on attacking/killing enemy
     * @param enemy enemy that is in range to be killed
     * @return true if enemy is in the attackRange radius
     */
    public boolean attack(Enemy enemy) {
        return ((((positionX + 100) - enemy.getPositionX()) * ((positionX + 100)
                - enemy.getPositionX()) + (((positionY + 80) - enemy.getPositionY())
                * ((positionY + 80) - enemy.getPositionY()))
                <= attackRange * attackRange));
    }
}
