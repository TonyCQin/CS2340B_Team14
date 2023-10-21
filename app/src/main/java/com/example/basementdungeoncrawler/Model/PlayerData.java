package com.example.basementdungeoncrawler.Model;

import com.example.basementdungeoncrawler.R;

public class PlayerData {

    private String username;
    private int spriteSelected;
    private int HP;
    private double positionX;
    private double positionY;

    private static volatile PlayerData playerData;

    /*
     * @param username username of playerData
     * @param spriteSelected int representing which sprite was selected by user
     */
    private PlayerData(String username, int spriteSelected) {
        this.username = username;
        this.spriteSelected = spriteSelected;
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
}
