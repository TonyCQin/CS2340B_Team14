package com.example.basementdungeoncrawler.Model;

public class Movement implements MovementInter {
    private PlayerData player;
    private Collision collision;
    private Enemy enemy;
    private int speed;
    private char direction = ' ';
    public Movement(PlayerData player, Collision collision) {
        this.player = player;
        this.collision = collision;
    }
    @Override
    public void walk(char direction) {
        switch (direction) {
        case 'w':
            if (!collision.getUp()) {
                player.setPositionY(player.getPositionY() - 16);
            }
            break;
        case 'a':
            if (!collision.getLeft()) {
                player.setPositionX(player.getPositionX() - 16);
            }
            break;
        case 's':
            if (!collision.getBottom()) {
                player.setPositionY(player.getPositionY() + 16);
            }
            break;
        case 'd':
            if (!collision.getRight()) {
                player.setPositionX(player.getPositionX() + 16);
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
                player.setPositionY(player.getPositionY() - 48);
            }
            break;
        case 'A':
            if (!collision.getLeft()) {
                player.setPositionX(player.getPositionX() - 48);
            }
            break;
        case 'S':
            if (!collision.getBottom()) {
                player.setPositionY(player.getPositionY() + 48);
            }
            break;
        case 'D':
            if (!collision.getRight()) {
                player.setPositionX(player.getPositionX() + 48);
            }
            break;
        default:
            break;
        }
        player.notifySubscribers();
    }
}
