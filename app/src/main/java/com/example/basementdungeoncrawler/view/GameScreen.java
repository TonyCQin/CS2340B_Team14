package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class GameScreen extends AppCompatActivity {
    private int hitPoints;
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
            Intent die = new Intent(GameScreen.this, EndScreen.class);
            startActivity(die);
        });

        // Initialize the views
        TextView name = findViewById(R.id.UserOutput);
        TextView charHealth = findViewById(R.id.HP);
        ImageView charSprite = findViewById(R.id.charViewSprite);

        // Retrieve data from ViewModels
        String nameHelper = playerViewModel.getUsername();
        name.setText(nameHelper);

        int difficultyHelper = gameViewModel.getDifficulty();
        if (difficultyHelper == 3) {
            hitPoints = 100;
        } else if (difficultyHelper == 2) {
            hitPoints = 150;
        } else if (difficultyHelper == 1) {
            hitPoints = 200;
        }
        charHealth.setText(String.valueOf(hitPoints));

        int characterNumber = playerViewModel.getSprite();
        if (characterNumber == 1) {
            charSprite.setImageResource(R.drawable.idle_crop1);
        } else if (characterNumber == 2) {
            charSprite.setImageResource(R.drawable.pumpkin_crop);
        } else if (characterNumber == 3) {
            charSprite.setImageResource(R.drawable.doc_crop);
        }
    }
}
