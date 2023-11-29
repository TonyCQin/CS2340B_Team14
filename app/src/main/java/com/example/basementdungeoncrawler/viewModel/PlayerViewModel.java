
package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.basementdungeoncrawler.Model.PlayerData;

public class PlayerViewModel extends ViewModel {

    private PlayerData playerData;

    public PlayerViewModel() {
        playerData = PlayerData.getPlayer();
    }

    //sets username
    public void setUsername(String newUsername) {
        playerData.setUsername(newUsername);
    }

    public String getUsername() {
        return playerData.getUsername();
    }

    //sets user sprite
    public void setSprite(int newSprite) {
        playerData.setSpriteSelected(newSprite);
    }

    public int getSprite() {
        return playerData.getSpriteSelected();
    }

    public void setHP(int newHP) {
        playerData.setHp(newHP);
    }

    public int getHP() {
        return playerData.getHp();
    }

    public double getX() {
        return playerData.getPositionX();
    }

    public double getY() {
        return playerData.getPositionY();
    }
    public double getAttackRadius() { return playerData.getAttackRadius(); }
    public void setX(double positionX) { playerData.setPositionX(positionX); }
    public void setY(double positionY) {
        playerData.setPositionY(positionY);
    }
    public void setAttackRadius(double attackRadius) { playerData.setAttackRadius(attackRadius); }
}