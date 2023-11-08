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

    public void enemyMove(int speed) {
//        int pace = 0;
//        while (enemy.getHP() > 0) {
//            if (pace % 5 != 0) {
//                direction = enemy.getRandomDirection();
//            }
//            int enemySpeed = enemy.getSpeed();
//            if (enemySpeed < 30) {
//                walk(direction);
//            } else {
//                run(direction);
//            }
//            pace++;
//        }
        int pace = 0;
        while (enemy.getHP() > 0) {
            if (pace % 3 != 0) {
               direction = enemy.getRandomDirection();
            }

            switch (direction) {
                case 'W':
                    if (!collision.getUp()) {
                        enemy.setPositionY(enemy.getPositionY() - speed);
                    }
                    break;
                case 'A':
                    if (!collision.getLeft()) {
                        enemy.setPositionX(enemy.getPositionX() - speed);
                    }
                    break;
                case 'S':
                    if (!collision.getBottom()) {
                        enemy.setPositionY(enemy.getPositionY() + speed);
                    }
                    break;
                case 'D':
                    if (!collision.getRight()) {
                        enemy.setPositionX(enemy.getPositionX() + speed);
                    }
                    break;
                default:
                    break;
            }
            pace++;
        }
    }
}
