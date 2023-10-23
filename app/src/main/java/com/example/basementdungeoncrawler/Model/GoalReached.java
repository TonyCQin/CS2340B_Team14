package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.view.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalReached {
    private final ArrayList<Integer> FinishIds = new ArrayList<>(Arrays.asList(17, 18, 19, 42, 43, 44, 67, 68, 69));

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
//        Log.d("tileid", String.valueOf(tilemap.getTile(positionX, positionY).getTileId()));
        return tilemap.getTile(positionX, positionY).getTileId();
    }
}
