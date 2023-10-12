package com.example.basementdungeoncrawler.Model;

import com.example.basementdungeoncrawler.R;

public class Player {

    private String username;
    private int spriteSelected;
    private int HP;

    private static volatile Player player;

    /*
     * @param username username of player
     * @param spriteSelected int representing which sprite was selected by user
     */
    private Player(String username, int spriteSelected) {
        this.username = username;
        this.spriteSelected = spriteSelected;
    }

    private Player() {
        this("", 0);
    }

    /*
     * Name: getPlayer()
     * @return the instance of the player
     */
    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
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
