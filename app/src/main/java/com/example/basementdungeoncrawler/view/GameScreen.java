package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;

public class GameScreen extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private GameViewModel gameViewModel;
    //private ImageView mapImageView;
    //private MapOneLayout tilemapOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the game screen
//        TmxView tmxView = new TmxView(this);
//        setContentView(tmxView);
        setContentView(R.layout.game_screen);
        //view models
        //private int hitPoints;
        PlayerViewModel playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        //connecting the buttons, name, character health,
        Button toEndScreen = findViewById(R.id.toEndButton);
        toEndScreen.setOnClickListener(v -> {
            addScore(playerViewModel.getUsername(), gameViewModel.getScore());
            Intent die = new Intent(GameScreen.this, EndScreen.class);
            startActivity(die);
        });

        // Initialize the views
        TextView name = findViewById(R.id.UserOutput);
        TextView charHealth = findViewById(R.id.HP);
        ImageView charSprite = findViewById(R.id.charViewSprite);

        // Retrieve data from ViewModels
        name.setText(playerViewModel.getUsername());

        charHealth.setText(String.valueOf(playerViewModel.getHP()));

        charSprite.setImageResource(playerViewModel.getSprite());

        TextView score = findViewById(R.id.score);
        score.setText("60");

        startTimer(60000, score);
        Button nextScreen2;
        Button nextScreen3;
        final ConstraintLayout constraintLayout = findViewById(R.id.layout);
        nextScreen2 = findViewById(R.id.nextScreen);
        nextScreen3 = findViewById(R.id.nextScreen2);
        nextScreen3.setVisibility(View.GONE);
        nextScreen2.setOnClickListener(view -> {
            constraintLayout.setBackgroundResource(R.drawable.map2);
            nextScreen2.setVisibility(View.GONE);
            nextScreen3.setVisibility(View.VISIBLE);
        });

        nextScreen3.setOnClickListener(view -> {
            constraintLayout.setBackgroundResource(R.drawable.map3);
            nextScreen3.setVisibility(View.GONE);
        });
    }

    private void startTimer(long milliseconds, TextView score) {
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
    }

    private void addScore(String username, int finalScore) {
        gameViewModel.addListScore(username, finalScore);
    }
}