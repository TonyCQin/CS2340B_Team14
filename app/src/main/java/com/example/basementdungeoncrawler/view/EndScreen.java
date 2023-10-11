package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.LeaderBoardAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        ArrayList<String> l1 = new ArrayList<>();
        l1.add("item1");
        l1.add("item2");
        l1.add("item3");
        l1.add("item4");
        l1.add("item5");

        ArrayList<String> l2 = new ArrayList<>();
        l2.add("sub item1");
        l2.add("sub item2");
        l2.add("sub item3");
        l2.add("sub item4");
        l2.add("sub item5");

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(EndScreen.this, l1, l2);
        ListView leaderBoard = findViewById(R.id.leaderBoard);
        leaderBoard.setAdapter(adapter);
    }
}

