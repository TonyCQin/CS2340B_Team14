package com.example.basementdungeoncrawler.Model;

public class Movement implements Action {
    private PlayerData player;

    public Movement(PlayerData player) {
        this.player = player;
    }
    @Override
    public void walk(char direction) {
        switch(direction) {
            case 'w':
                player.setPositionY(player.getPositionY() - 16);
                break;
            case 'a':
                player.setPositionX(player.getPositionX() - 16);
                break;
            case 's':
                player.setPositionY(player.getPositionY() + 16);
                break;
            case 'd':
                player.setPositionX(player.getPositionX() + 16);
                break;
        }
        player.notifySubscribers();
    }

    @Override
    public void run(char direction) {
        switch(direction) {
            case 'W':
                player.setPositionY(player.getPositionY() - 48);
                break;
            case 'A':
                player.setPositionX(player.getPositionX() - 48);
                break;
            case 'S':
                player.setPositionY(player.getPositionY() + 48);
                break;
            case 'D':
                player.setPositionX(player.getPositionX() + 48);
                break;
        }
        player.notifySubscribers();
    }
}
