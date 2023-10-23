package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.view.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalReached implements PlayerSubscriber {
    private final ArrayList<Integer> FinishIds = new ArrayList<>(Arrays.asList(17, 18, 19, 42, 43, 44, 67, 68, 69));


    private TileMap tileMap;
    private boolean isGoalReached;
    private static volatile GoalReached goalReached;


    public GoalReached(TileMap t) {
        tileMap = t;
    }

    public static GoalReached getGoalReached(Context context) {
        if (goalReached == null) {
            synchronized (PlayerData.class) {
                if (goalReached == null) {
                    TileMap map3TileMap = new TileMap(context, R.raw.new_map_3);
                    goalReached = new GoalReached(map3TileMap);
                }
            }
        }
        return goalReached;
    }



    public void checkGoalReached(double x, double y) {
        Log.d("x", String.valueOf(x));
        Log.d("y", String.valueOf(y));
        setIsGoalReached(FinishIds.contains(getTileId(tileMap, x, y)));
        Log.d("IsGoalReached", String.valueOf(isGoalReached));
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

    @Override
    public void update(double positionX, double positionY, double radius) {
        checkGoalReached(positionX, positionY);
    }
}
