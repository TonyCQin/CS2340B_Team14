
package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.Player;
import com.example.basementdungeoncrawler.R;

public class PlayerViewModel extends ViewModel {

    private Player player;

    public PlayerViewModel() {
        player = Player.getPlayer();
    }

    //sets username
    public void setUsername(String newUsername) {
        player.setUsername(newUsername);
    }

    public String getUsername() {
        return player.getUsername();
    }

    //sets user sprite
    public void setSprite(int newSprite) {
        player.setSpriteSelected(newSprite);
    }

    public int getSprite() {
        return player.getSpriteSelected();
    }

    public void setHP(int newHP) {
        player.setHP(newHP);
    }

    public int getHP() {
        return player.getHP();
    }
}