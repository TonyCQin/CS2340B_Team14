package com.example.basementdungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {
    private Button quitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this connects to design of start screen
        setContentView(R.layout.start_screen);
        //this is setting up the quit button
        quitButton = findViewById(R.id.QuitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartScreen.this.finish();
                System.exit(0);
            }
        });
    }
    //takes you to the next screen; parameters: current screen to screen you want to go to
    public void mainActivity(View view) {
        startActivity(new Intent(StartScreen.this, MainActivity.class));
    }
}
