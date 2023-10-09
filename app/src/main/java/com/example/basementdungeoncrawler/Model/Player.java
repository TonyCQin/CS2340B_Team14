package com.example.basementdungeoncrawler.Model;
public class Player {

    private String username;
    private int spriteSelected;

    private volatile static Player player;

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
        spriteSelected = newSprite;
    }
}
