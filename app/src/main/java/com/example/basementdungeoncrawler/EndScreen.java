package com.example.basementdungeoncrawler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EndScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the end screen
        setContentView(R.layout.end_screen);
        //sets up the restart button
        Button toStartScreen = findViewById(R.id.toStartScreen);
        toStartScreen.setOnClickListener(v -> {
            Intent restart = new Intent(EndScreen.this, MainActivity.class);
            startActivity(restart);
        });
    }
}
