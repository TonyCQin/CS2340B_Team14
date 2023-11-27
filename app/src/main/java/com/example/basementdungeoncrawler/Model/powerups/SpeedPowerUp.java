package com.example.basementdungeoncrawler.Model.powerups;

import com.example.basementdungeoncrawler.Model.PlayerSubscriber;
import com.example.basementdungeoncrawler.R;

public class SpeedPowerUp implements PowerUp, PlayerSubscriber {
    private double x;
    private double y;
    private double speed;
    private int sprite;
    private PowerUpNotifier notifier;

    public SpeedPowerUp(int x, int y, PowerUpNotifier notifier) {
        this.x = x;
        this.y = y;
        speed = 2;
        sprite = R.drawable.green_potion;
        this.notifier = notifier;
    }

    public void render() {

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
        }
    }

    public boolean checkCollision(double positionX, double positionY) {
        return x - 16 < positionX || positionX < x + 16
                && y - 16 < positionY || positionY < y + 16;
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
}
