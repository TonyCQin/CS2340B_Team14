package com.example.basementdungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class GameScreen extends AppCompatActivity{
    private Button toEndScreen;
//    private TextView name = findViewById(R.id.UserOutput);
//    private TextView charHealth = findViewById(R.id.HP);
//    private ImageView charSprite = findViewById(R.id.charViewSprite);
//    private int HP;

    private TextView name;
    private TextView charHealth;
    private ImageView charSprite;
    private int HP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the game screen
        setContentView(R.layout.game_screen);
        //connecting the buttons, name, character health,
        toEndScreen = findViewById(R.id.toEndButton);
        toEndScreen.setOnClickListener(v -> {
            Intent die = new Intent(GameScreen.this, EndScreen.class);
            startActivity(die);
        });

        // Initialize the views
        name = findViewById(R.id.UserOutput);
        charHealth = findViewById(R.id.HP);
        charSprite = findViewById(R.id.charViewSprite);

        // Retrieve intent data
        Intent intent = getIntent();
        String nameHelper = intent.getStringExtra("username");
        name.setText(nameHelper);

        Double difficultyHelper = intent.getDoubleExtra("difficultyWanted", 1);
        if (difficultyHelper == 2) {
            HP = 100;
        } else if (difficultyHelper == 1.5) {
            HP = 150;
        } else if (difficultyHelper == 1) {
            HP = 200;
        }
        charHealth.setText(String.valueOf(HP));

        int characterNumber = intent.getIntExtra("charSelected", 1);
        if (characterNumber == 1) {
            charSprite.setImageResource(R.drawable.idle_crop1);
        } else if (characterNumber == 2) {
            charSprite.setImageResource(R.drawable.idle_crop1);
        } else if (characterNumber == 3) {
            charSprite.setImageResource(R.drawable.idle_crop1);
        }

//        Intent displayUsername = getIntent();
//        String nameHelper = displayUsername.getStringExtra("username");
//        name.setText(nameHelper);

//        Intent displayHealth = getIntent();
//        Double difficultyHelper = displayHealth.getDoubleExtra("difficultyWanted", 1);
//        if (difficultyHelper == 2) {
//            HP = 100;
//        } else if (difficultyHelper == 1.5) {
//            HP = 150;
//        } else if (difficultyHelper == 1) {
//            HP = 200;
//        }
//        charHealth.setText(HP);

//        Intent charWanted = getIntent();
//        int characterNumber = charWanted.getIntExtra("charSelected", 1);
//        if (characterNumber == 1) {
//            charSprite.setImageResource(R.drawable.idle);
//        } else if (characterNumber == 2) {
//            charSprite.setImageResource(R.drawable.idle);
//        } else if (characterNumber == 3) {
//            charSprite.setImageResource(R.drawable.idle);
//        }
    }
}
