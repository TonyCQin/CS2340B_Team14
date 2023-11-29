package com.example.basementdungeoncrawler.Model.powerups;

import com.example.basementdungeoncrawler.Model.PlayerData;

public class PowerUpNotifier {
    PlayerData playerData = PlayerData.getPlayer();
    public void apply(PowerUp p) {
        playerData.apply(p);
    }
}
