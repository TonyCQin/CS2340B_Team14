package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.os.CountDownTimer;
import android.graphics.Paint;
import android.util.Log;

import com.example.basementdungeoncrawler.Model.powerups.HPPowerUp;
import com.example.basementdungeoncrawler.Model.powerups.InvincabilityPowerUp;
import com.example.basementdungeoncrawler.Model.powerups.PowerUp;
import com.example.basementdungeoncrawler.Model.powerups.SpeedPowerUp;
import com.example.basementdungeoncrawler.R;

public class PlayerData {

    private String username;
    private int spriteSelected;
    private int hp;
    private double positionX;
    private double positionY;
    private double radius;
    private boolean invincible;

    private static volatile PlayerData playerData;
    private double speed;


    /*
     * @param username username of playerData
     * @param spriteSelected int representing which sprite was selected by user
     */
    private PlayerData(String username, int spriteSelected) {
        this.username = username;
        this.spriteSelected = spriteSelected;
    }

    public PlayerData(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        speed = 1;
        invincible = false;
    }
  
    private PlayerData() {
        this("", 0);
    }

    /*
     * Name: getPlayer()
     * @return the instance of the playerData
     */
    public static PlayerData getPlayer() {
        if (playerData == null) {
            synchronized (PlayerData.class) {
                if (playerData == null) {
                    playerData = new PlayerData();
                }
            }
        }
        return playerData;
    }

    /*
     * @return get username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    /*
     * @return get spriteSelected
     */
    public int getSpriteSelected() {
        return spriteSelected;
    }

    public void setSpriteSelected(int newSprite) {
        if (newSprite == 1) {
            spriteSelected = (R.drawable.knight_sheet);
        }
        if (newSprite == 2) {
            spriteSelected = (R.drawable.rogue_sheet);
        }
        if (newSprite == 3) {
            spriteSelected = (R.drawable.wizard_sheet);
        }
    }

    public void setHp(int hp) {
        if (!invincible) {
            this.hp = hp;
        }
    }

    public int getHp() {
        return hp;
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
    public void setSpeed(double newSpeed) {
        speed = newSpeed;
    }
    public double getSpeed() {
        return speed;
    }

    public void apply(PowerUp p) {
        if (p instanceof HPPowerUp) {
            hp += ((HPPowerUp) p).getHPIncrease();
            Log.d("adding hp", String.valueOf(((HPPowerUp) p).getHPIncrease()));
            Log.d("new hp", String.valueOf(hp));
        }

        if (p instanceof SpeedPowerUp) {
            speed = ((SpeedPowerUp) p).getSpeed();
            Log.d("setting speed", String.valueOf(((SpeedPowerUp) p).getSpeed()));
        }

        if (p instanceof InvincabilityPowerUp) {
            invincible = true;
            Log.d("starting timer", "");
            startInvincibilityTimer();
        }
    }

    private void startInvincibilityTimer() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("timer at", String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                invincible = false;
                Log.d("finished timer", "");
            }

        }.start();
    }
}
