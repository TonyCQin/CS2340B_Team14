package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.logic.Player;

import java.util.ArrayList;
import java.util.List;

public class GoalReach {
    Player currentPlayer;
    private double goalXCoord;
    private double goalYCoord;

    private boolean playerReachGoal;
    private List<Subscriber> subscribers = new ArrayList<>;

    public void notifyGoalReached() {
        if (currentPlayer.getPositionX() == goalXCoord && currentPlayer.getPositionY() == goalYCoord) {
            boolean x = true;
            currentPlayer.setReachGoal(x);
        }
    }
}
