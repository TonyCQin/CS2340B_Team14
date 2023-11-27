package com.example.basementdungeoncrawler.Model.powerups;

import com.example.basementdungeoncrawler.Model.PlayerSubscriber;
import com.example.basementdungeoncrawler.R;

public class HPPowerUp implements PowerUp, PlayerSubscriber {
    private double x;
    private double y;
    private int HPIncrease;
    private int sprite;
    private PowerUpNotifier notifier;

    public HPPowerUp(int x, int y, int hpIncrease, PowerUpNotifier notifier) {
        this.x = x;
        this.y = y;
        this.HPIncrease = hpIncrease;
        sprite = R.drawable.red_potion;
        this.notifier = notifier;
    }

    public int getHPIncrease() {
        return HPIncrease;
    }

    public void setHPIncrease(int newHPIncrease) {
        HPIncrease = newHPIncrease;
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
