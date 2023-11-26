package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.Model.powerups.PowerUp;
import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;

public class PlayerData {

    private String username;
    private int spriteSelected;
    private int hp;
    private double positionX;
    private double positionY;
    private double radius;
    private boolean invincable;

    private static volatile PlayerData playerData;


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
        invincable = false;
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
        if (invincable) {
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

    public void apply(PowerUp p) {
        System.out.println("doing something");
    }
}
