package com.example.basementdungeoncrawler.Model;

import android.util.Log;

public class Movement implements MovementInter {
    private Player player;
    private PlayerData playerData;
    private Collision collision;
    private Enemy enemy;
    private int speed;
    private char direction = ' ';
    public Movement(Player player, Collision collision) {
        this.player = player;
        this.collision = collision;
        playerData = PlayerData.getPlayer();
    }
    @Override
    public void walk(char direction) {
        Log.d("speed", String.valueOf(playerData.getSpeed()));
        double def = 16 * playerData.getSpeed();
        Log.d("amt", String.valueOf(def));
        switch (direction) {
        case 'w':
            if (!collision.getUp()) {
                player.setPositionY(player.getPositionY() - def);
            }
            break;
        case 'a':
            if (!collision.getLeft()) {
                player.setPositionX(player.getPositionX() - def);
            }
            break;
        case 's':
            if (!collision.getBottom()) {
                player.setPositionY(player.getPositionY() + def);
            }
            break;
        case 'd':
            if (!collision.getRight()) {
                player.setPositionX(player.getPositionX() + def);
            }
            break;
        default:
            break;
        }
        player.notifySubscribers();
    }

    @Override
    public void run(char direction) {
        switch (direction) {
        case 'W':
            if (!collision.getUp()) {
                player.setPositionY(player.getPositionY() - (48 * playerData.getSpeed()));
            }
            break;
        case 'A':
            if (!collision.getLeft()) {
                player.setPositionX(player.getPositionX() - (48 * playerData.getSpeed()));
            }
            break;
        case 'S':
            if (!collision.getBottom()) {
                player.setPositionY(player.getPositionY() + (48 * playerData.getSpeed()));
            }
            break;
        case 'D':
            if (!collision.getRight()) {
                player.setPositionX(player.getPositionX() + (48 * playerData.getSpeed()));
            }
            break;
        default:
            break;
        }
        player.notifySubscribers();
    }
}
