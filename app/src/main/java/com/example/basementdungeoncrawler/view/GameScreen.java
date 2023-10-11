package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.Model.Game;
import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class GameScreen extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the game screen
        setContentView(R.layout.game_screen);
        //view models
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
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