package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.Model.EdgeReached;
import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.Model.GoalReached;
import com.example.basementdungeoncrawler.Model.PlayerData;
import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;

import android.util.Log;

public class GameScreen extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private GameViewModel gameViewModel;
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //view models
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        //sets the view to the UI of the game screen

        //instantiate initial map
        PlayerData player = PlayerData.getPlayer();
        TileMap map1TileMap = new TileMap(this, R.raw.new_map1);
        gameViewModel.setScreenCounter(1);
        Log.d("tileMap", String.valueOf(map1TileMap.getLayers()));
        MapView mapView = new MapView(this, map1TileMap.getLayers(), map1TileMap, this, 400, 1600, 30);
        setContentView(mapView);
        //


        //connecting the buttons, name, character health,
        addEndScreenButton();
        addSpriteImageView();
        addUsernameTextView();
        addScoreTextView();
        addHPTextView();
        TextView score = findViewById(R.id.score);
        score.setText("60");
    }

    private void addScore(String username, int finalScore) {
        gameViewModel.addListScore(username, finalScore);
    }

    private void addEndScreenButton() {
        Button toEndScreen = new Button(this);
        toEndScreen.setOnClickListener(v -> {
            addScore(playerViewModel.getUsername(), gameViewModel.getScore());
            Intent die = new Intent(GameScreen.this, EndScreen.class);
            startActivity(die);
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 0; // X coordinate
        params.topMargin = 0;  // Y coordinate
        toEndScreen.setLayoutParams(params);
        toEndScreen.setText("DIE");
        toEndScreen.setTextColor(Color.WHITE);
        toEndScreen.setBackgroundColor(Color.BLACK);
        addContentView(toEndScreen, params);
    }

    private void addSpriteImageView() {
        ImageView sprite = new ImageView(this);
        sprite.setImageResource(playerViewModel.getSprite());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 300; // X coordinate
        params.topMargin = 0;  // Y coordinate
        sprite.setLayoutParams(params);
        addContentView(sprite, params);
    }

    private void addUsernameTextView() {
        TextView username = new TextView(this);
        username.setText(playerViewModel.getUsername());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 500; // X coordinate
        params.topMargin = 0;  // Y coordinate
        username.setLayoutParams(params);
        username.setTextColor(Color.WHITE);
        addContentView(username, params);
    }

    private void addScoreTextView() {
        TextView score = new TextView(this);
        score.setId(R.id.score);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 700; // X coordinate
        params.topMargin = 0;  // Y coordinate
        score.setLayoutParams(params);
        score.setTextColor(Color.WHITE);
        addContentView(score, params);
    }

    private void addHPTextView() {
        TextView HP = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 600; // X coordinate
        params.topMargin = 0;  // Y coordinate
        HP.setLayoutParams(params);
        HP.setTextColor(Color.WHITE);
        HP.setText(String.valueOf(playerViewModel.getHP()));
        addContentView(HP, params);
    }

    public void update() {
        int screenCounter = gameViewModel.getScreenCounter();

//        if (screenCounter == 1) {
//            TileMap map2TileMap = new TileMap(this, R.raw.new_map2);
//            Log.d("tileMap", String.valueOf(map2TileMap.getLayers()));
//            MapView map2View = new MapView(this, map2TileMap.getLayers(), map2TileMap, this, 200, 2000, 30);
//            EdgeReached.getEdgeReached().setIsEdgeReached(false);
//            setContentView(map2View);
//            //add buttons
//            addEndScreenButton();
//            addSpriteImageView();
//            addUsernameTextView();
//            addScoreTextView();
//            addHPTextView();
//            TextView score = findViewById(R.id.score);
//            score.setText(String.valueOf(gameViewModel.getScore()));
//            gameViewModel.setScreenCounter(2);
//        }
        if (screenCounter == 1) {
            //set map
            TileMap map3TileMap = new TileMap(this, R.raw.new_map_3);
            Log.d("tileMap", String.valueOf(map3TileMap.getLayers()));
            MapView map3View = new MapView(this, map3TileMap.getLayers(), map3TileMap, this, 200, 250, 30);
            EdgeReached.getEdgeReached().setIsEdgeReached(false);
            setContentView(map3View);
            //add buttons
            addEndScreenButton();
            addSpriteImageView();
            addUsernameTextView();
            addScoreTextView();
            addHPTextView();
            TextView score = findViewById(R.id.score);
            score.setText(String.valueOf(gameViewModel.getScore()));
        }
        Log.d("is it tho", String.valueOf(GoalReached.getGoalReached().getIsGoalReached()));
        if (GoalReached.getGoalReached().getIsGoalReached()) {

            Intent intent = new Intent(GameScreen.this, EndScreen.class);
            startActivity(intent);
        }
    }
}