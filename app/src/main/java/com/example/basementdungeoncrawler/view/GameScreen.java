package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class GameScreen extends AppCompatActivity {
    private int hitPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the game screen
        setContentView(R.layout.game_screen);
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

        // Retrieve intent data
        Intent intent = getIntent();
        String nameHelper = intent.getStringExtra("username");
        name.setText(nameHelper);

        int difficultyHelper = intent.getIntExtra("difficultyWanted", 1);
        if (difficultyHelper == 3) {
            hitPoints = 100;
        } else if (difficultyHelper == 2) {
            hitPoints = 150;
        } else if (difficultyHelper == 1) {
            hitPoints = 200;
        }
        charHealth.setText(String.valueOf(hitPoints));

        int characterNumber = intent.getIntExtra("charSelected", 1);
        if (characterNumber == 1) {
            charSprite.setImageResource(R.drawable.idle_crop1);
        } else if (characterNumber == 2) {
            charSprite.setImageResource(R.drawable.pumpkin_crop);
        } else if (characterNumber == 3) {
            charSprite.setImageResource(R.drawable.doc_crop);
        }
    }
}
