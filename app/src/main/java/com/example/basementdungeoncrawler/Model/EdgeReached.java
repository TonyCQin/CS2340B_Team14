package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.util.Log;

import com.example.basementdungeoncrawler.view.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class EdgeReached implements PlayerSubscriber {
    private Boolean isEdgeReached = false;
    private int height;
    private int width;
    private static volatile EdgeReached edgeReached;
    private final ArrayList<Integer> finishIds = new ArrayList<>(Arrays.asList(17, 18, 19, 42,
        43, 44, 67, 68, 69));


    public EdgeReached(int height, int width) {
        this.height = height;
        this.width = width;
        this.isEdgeReached = false;
    }

    public void update(double x, double y) {
        checkEdgeReached(x, y);
    }

    public void checkEdgeReached(double x, double y) {
        if (x > width || y > height) {
            isEdgeReached = true;
        } else {
            isEdgeReached = false;
        }
    }

    public Boolean getIsEdgeReached() {
        return isEdgeReached;
    }

    public void setIsEdgeReached(boolean isEdgeReached) {
        this.isEdgeReached = isEdgeReached;
    }

    public static EdgeReached getEdgeReached() {
        if (edgeReached == null) {
            synchronized (PlayerData.class) {
                if (edgeReached == null) {
                    edgeReached = new EdgeReached(4000, 4000);
                }
            }
        }
        return edgeReached;
    }
}
