package com.example.basementdungeoncrawler.Model;

import com.example.basementdungeoncrawler.graphics.TileMap;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalReached {
    private final ArrayList<Integer> finishIds = new ArrayList<>(Arrays.asList(17, 18, 19, 42,
        43, 44, 67, 68, 69));

    private boolean isGoalReached;
    private static volatile GoalReached goalReached;


    public GoalReached() {
        this.isGoalReached = false;
    }

    public static GoalReached getGoalReached() {
        if (goalReached == null) {
            synchronized (PlayerData.class) {
                if (goalReached == null) {
                    goalReached = new GoalReached();
                }
            }
        }
        return goalReached;
    }

    public boolean getIsGoalReached() {
        return isGoalReached;
    }

    public void setIsGoalReached(boolean isGoalReached) {
        this.isGoalReached = isGoalReached;
    }

    private int getTileId(TileMap tilemap, double positionX, double positionY) {
        return tilemap.getTile(positionX, positionY).getTileId();
    }
}
