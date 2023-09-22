package com.example.basementdungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class GameScreen extends AppCompatActivity{
    private Button toEndScreen;
    private TextView name;
    private TextView charHealth;
    private ImageView charSprite;

    @Override
    protected void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        //sets the view to the UI of the game screen
        setContentView(R.layout.game_screen);
        //connecting the buttons, name, character health,
        toEndScreen = findViewById(R.id.toEndButton);
        toEndScreen.setOnClickListener(v -> {
            Intent die = new Intent(GameScreen.this, EndScreen.class);
            startActivity(die);
        });

        Intent displayUsername = new Intent();
    }
}
