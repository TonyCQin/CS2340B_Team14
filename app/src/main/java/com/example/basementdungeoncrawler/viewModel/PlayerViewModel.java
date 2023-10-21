
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
        playerData.setHP(newHP);
    }

    public int getHP() {
        return playerData.getHP();
    }
}