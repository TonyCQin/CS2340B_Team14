
package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.Model.Player;

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
}