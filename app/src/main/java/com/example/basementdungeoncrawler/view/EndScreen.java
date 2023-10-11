package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.R;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
        ListView leaderBoard = findViewById(R.id.leaderBoard);
        // Define the data you want to display in the ListView
        String[] data = {"player 1", "player 2", "player 3", "player 4", "player 5"};
        ScoresList scoreslist = ScoresList.getList();
//        TODO: scoreslist.getTopFive.list
//        pass that into the adapter

        // Create an ArrayAdapter to bind the data to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        // Set the adapter to the ListView
        leaderBoard.setAdapter(adapter);
    }
}

