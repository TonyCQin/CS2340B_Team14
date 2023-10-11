package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.EndScreenViewModel;
import com.example.basementdungeoncrawler.viewModel.LeaderBoardAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class EndScreen extends AppCompatActivity {
    private EndScreenViewModel endScreenViewModel;
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

        endScreenViewModel = new ViewModelProvider(this).get(EndScreenViewModel.class);
        ArrayList<String> l1 = endScreenViewModel.getUserNames();
        ArrayList<String> l2 = endScreenViewModel.getScoreAndTime();

        TextView recentUserName = findViewById(R.id.line1TextViewRecent);
        TextView recentScoreTime = findViewById(R.id.line2TextViewRecent);
        recentUserName.setText(endScreenViewModel.getRecentUserName());
        recentScoreTime.setText(endScreenViewModel.getRecentScoreAndTime());

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(EndScreen.this, l1, l2);
        ListView leaderBoard = findViewById(R.id.leaderBoard);
        leaderBoard.setAdapter(adapter);
    }
}

