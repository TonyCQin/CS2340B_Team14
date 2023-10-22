package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class PlayerData {

    private String username;
    private int spriteSelected;
    private int HP;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private boolean goalReached;

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

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }

    public void move(char direction) {
        switch(direction) {
            case 'w':
                positionY = positionY - 16;
                break;
            case 'a':
                positionX = positionX - 16;
                break;
            case 's':
                positionY = positionY + 16;
                break;
            case 'd':
                positionX = positionX + 16;
                break;
            case 'W':
                positionY = positionY - 48;
                break;
            case 'A':
                positionX = positionX - 48;
                break;
            case 'S':
                positionY = positionY + 48;
                break;
            case 'D':
                positionX = positionX + 48;
                break;
        }
    }

    public double getPositionX(){
        return positionX;
    }
    public double getPositionY(){
        return positionY;
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
            spriteSelected = (R.drawable.idle_crop1);
        }
        if (newSprite == 2) {
            spriteSelected = (R.drawable.pumpkin_crop);
        }
        if (newSprite == 3) {
            spriteSelected = (R.drawable.doc_crop);
        }
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }
    public boolean isGoalReached()
    {
        return goalReached;
    }
}
