package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.Model.Collision;
import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.graphics.TmxParser;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;


import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.util.Log;

import org.w3c.dom.Text;

import java.util.List;

public class GameScreen extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private GameViewModel gameViewModel;
    //private ImageView mapImageView;
    //private MapOneLayout tilemapOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the game screen

        //testing
        TileMap map1TileMap = new TileMap(this, R.raw.new_map1);
        Log.d("tileMap", String.valueOf(map1TileMap.getLayers()));
        MapView mapView = new MapView(this, map1TileMap.getLayers(), map1TileMap);
        setContentView(mapView);
        //

//        setContentView(R.layout.game_screen);
//        view models
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        //connecting the buttons, name, character health,
        addEndScreenButton();

        addSpriteImageView();
        addUsernameTextView();
        addScoreTextView();
        addHPTextView();
//
        TextView score = findViewById(R.id.score);
        score.setText("60");
//


        addNext2Button(startTimer(60000, score));
//        Button nextScreen2;
//        Button nextScreen3;
//        final ConstraintLayout constraintLayout = findViewById(R.id.layout);
//        nextScreen2 = findViewById(R.id.nextScreen);
//        nextScreen3 = findViewById(R.id.nextScreen2);
//        nextScreen3.setVisibility(View.GONE);
//        nextScreen2.setOnClickListener(view -> {
//            constraintLayout.setBackgroundResource(R.drawable.map2);
//            nextScreen2.setVisibility(View.GONE);
//            nextScreen3.setVisibility(View.VISIBLE);
//        });
//
//        nextScreen3.setOnClickListener(view -> {
//            constraintLayout.setBackgroundResource(R.drawable.map3);
//            nextScreen3.setVisibility(View.GONE);
//        });

    }

    private CountDownTimer startTimer(long milliseconds, TextView score) {
        CountDownTimer timer = new CountDownTimer(milliseconds, 1000) {
            @Override
            //ticks every seconds based on interval specified in CountDownTimer
            //checks the score stored in Game, decrements it for the next call
            public void onTick(long millisecondsLeft) {
                int newScore = gameViewModel.getScore();
                score.setText(String.valueOf(newScore));
                gameViewModel.setScore(newScore - 1);
            }

            //self explanatory method
            public void onFinish() {
                score.setText(0);
            }
        };

        timer.start();
        return timer;
    }

    private void stopTimer(CountDownTimer timer) {
        timer.cancel();
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

    private void addNext2Button(CountDownTimer timer) {
        Button next = new Button(this);
        next.setOnClickListener(v -> {
            TileMap map2TileMap = new TileMap(this, R.raw.new_map2);
            Log.d("tileMap", String.valueOf(map2TileMap.getLayers()));
            MapView mapView = new MapView(this, map2TileMap.getLayers(), map2TileMap);

            setContentView(mapView);
            addEndScreenButton();

            addSpriteImageView();
            addUsernameTextView();
            addScoreTextView();
            addHPTextView();
            timer.cancel();
            TextView score = findViewById(R.id.score);
            score.setText(String.valueOf(gameViewModel.getScore()));
            addNext3Button(startTimer(gameViewModel.getScore() * 1000, score));
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 0; // X coordinate
        params.topMargin = 150;  // Y coordinate
        next.setLayoutParams(params);
        next.setText("NEXT");
        next.setTextColor(Color.BLACK);
        next.setBackgroundColor(Color.WHITE);
        addContentView(next, params);
    }

    private void addNext3Button(CountDownTimer timer) {
        Button next = new Button(this);
        next.setOnClickListener(v -> {
            TileMap map3TileMap = new TileMap(this, R.raw.new_map3);
            Log.d("tileMap", String.valueOf(map3TileMap.getLayers()));
            MapView mapView = new MapView(this, map3TileMap.getLayers(), map3TileMap);

            setContentView(mapView);
            addEndScreenButton();
            addSpriteImageView();
            addUsernameTextView();
            addScoreTextView();
            addHPTextView();
            timer.cancel();
            TextView score = findViewById(R.id.score);
            score.setText(String.valueOf(gameViewModel.getScore()));
            startTimer(gameViewModel.getScore() * 1000, score);
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 0; // X coordinate
        params.topMargin = 150;  // Y coordinate
        next.setLayoutParams(params);
        next.setText("NEXT");
        next.setTextColor(Color.BLACK);
        next.setBackgroundColor(Color.WHITE);
        addContentView(next, params);
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
}