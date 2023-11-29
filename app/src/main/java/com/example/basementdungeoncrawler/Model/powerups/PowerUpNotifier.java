package com.example.basementdungeoncrawler.Model.powerups;

import com.example.basementdungeoncrawler.Model.PlayerData;

public class PowerUpNotifier {
    private PlayerData playerData = PlayerData.getPlayer();
    public void apply(PowerUp p) {
        playerData.apply(p);
    }
}
