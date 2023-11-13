package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.EndScreenViewModel;
import com.example.basementdungeoncrawler.viewModel.LeaderBoardAdapter;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class EndScreen extends AppCompatActivity {
    private EndScreenViewModel endScreenViewModel;
    private PlayerViewModel playerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to the UI of the end screen
        setContentView(R.layout.end_screen);

        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        // you won alert
        if (playerViewModel.getHP() <= 0) {
            showLossAlert();
        } else {
            showWinAlert();
        }

        //sets up the restart button
        Button toStartScreen = findViewById(R.id.toStartScreen);
        toStartScreen.setOnClickListener(v -> {
            Intent restart = new Intent(EndScreen.this, MainActivity.class);
            startActivity(restart);
        });

        endScreenViewModel = new ViewModelProvider(this).get(EndScreenViewModel.class);
        ArrayList<String> l1 = endScreenViewModel.getUserNamesAndScores();
        ArrayList<String> l2 = endScreenViewModel.getTime();

        TextView recentUserName = findViewById(R.id.line1TextViewRecent);
        TextView recentScoreTime = findViewById(R.id.line2TextViewRecent);
        recentUserName.setText(endScreenViewModel.getRecentUserNameAndScore());
        recentScoreTime.setText(endScreenViewModel.getRecentTime());

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(EndScreen.this, l1, l2);
        ListView leaderBoard = findViewById(R.id.leaderBoard);
        leaderBoard.setAdapter(adapter);
    }

    private void showWinAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You won!")
                .setMessage("You won!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showLossAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You lost :(")
                .setMessage("You lost :(")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}

