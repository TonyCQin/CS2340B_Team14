package com.example.basementdungeoncrawler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigScreen extends AppCompatActivity {
    private RadioGroup difficulties;
    private RadioButton difficultySelectedButton;
    private TextView difficultyLevelText;
    private String difficultyText;
    private Boolean difficultyIsSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);

        //username check
        EditText usernameInput = findViewById(R.id.editText);

        final String[] username = {""};

        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username[0] = usernameInput.getText().toString();
                TextView usernameText = findViewById(R.id.username);
                usernameText.setText(username[0]);
//                Intent displayUsername = new Intent(MainActivity.this, GameScreen.class);
                // Add the username data to the intent using the key "username"
//                displayUsername.putExtra("username", username[0]);
//                startActivity(displayUsername);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        //-------------------------------------------------------------

        //this text view is for debugging for now
        TextView charText = findViewById(R.id.charTextView);

        //character select
        Button char1 = findViewById(R.id.char1);
        Button char2 = findViewById(R.id.char2);
        Button char3 = findViewById(R.id.char3);
        final boolean[] charSelected = {false};
        final int[] charIdentifierSelected = {0};

        char1.setOnClickListener(a -> {
            charText.setText("Character 1 selected");
            charSelected[0] = true;
            charIdentifierSelected[0] = 1;

//            Intent char1Transfer = new Intent(MainActivity.this, GameScreen.class);
//            char1Transfer.putExtra("charSelected", 1);
        });

        char2.setOnClickListener(b -> {
            charText.setText("Character 2 selected");
            charSelected[0] = true;
            charIdentifierSelected[0] = 2;
//            Intent char2Transfer = new Intent(MainActivity.this, GameScreen.class);
//            char2Transfer.putExtra("charSelected", 2);
        });

        char3.setOnClickListener(c -> {
            charText.setText("Character 3 selected");
            charSelected[0] = true;
            charIdentifierSelected[0] = 3;
//            Intent char3Transfer = new Intent(MainActivity.this, GameScreen.class);
//            char3Transfer.putExtra("charSelected", 3);
        });

        //---------------------------------------------------------------------

        //difficulty config
        difficulties = findViewById(R.id.difficultyRadioGroup);
        difficultyLevelText = findViewById(R.id.difficultyLevel);
        final int[] difficultySelected = {0};

        int radioDifficultyID = difficulties.getCheckedRadioButtonId();
        difficultySelectedButton = findViewById(radioDifficultyID);

        RadioButton easyButton = findViewById(R.id.difficultyEasy);
        RadioButton mediumButton = findViewById(R.id.difficultyMedium);
        RadioButton hardButton = findViewById(R.id.difficultyHard);

        if (difficultySelectedButton.equals(easyButton)) {
            difficultyIsSelected = true;
            difficultySelected[0] = 1;
            difficultyText = "Easy mode is selected";
        } else if (difficultySelectedButton.equals(mediumButton)) {
            difficultyIsSelected = true;
            difficultySelected[0] = 2;
            difficultyText = "Medium mode is selected";
        } else if (difficultySelectedButton.equals(hardButton)) {
            difficultyIsSelected = true;
            difficultySelected[0] = 3;
            difficultyText = "Hard mode is selected";
        } else {
            difficultyText = "No Difficulty Selected";
        }

        difficultyLevelText.setText(difficultyText);
        /*
        difficulties.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onRadioButtonClicked(RadioGroup radioGroup, int i) {
                int difficulty = 1;
                String difficultyText;
                RadioButton easyButton = findViewById(R.id.difficultyEasy);
                RadioButton mediumButton = findViewById(R.id.difficultyMedium);
                RadioButton hardButton = findViewById(R.id.difficultyHard);

                if (hardButton.isChecked()) {
                    difficulty = 3;
                    difficultyIsSelected[0] = true;
                    difficultySelected[0] = difficulty;
                } else if (mediumButton.isChecked()) {
                    difficulty = 2;
                    difficultyIsSelected[0] = true;
                    difficultySelected[0] = difficulty;
                } else if (easyButton.isChecked()) {
                    difficulty = 1;
                    difficultyIsSelected[0] = true;
                    difficultySelected[0] = difficulty;
                }

                if (difficulties.getCheckedRadioButtonId() == -1) {
                    difficultyText = "No difficulty selected";
                    difficultyIsSelected[0] = false;
                } else {
                    difficultyText = String.format("Difficulty : %.1f", difficulty);
                }

                difficultyLevelText.setText(difficultyText);

//                Intent setHPonDifficulty = new Intent(MainActivity.this, GameScreen.class);
                // Add the difficulty data to the intent using the key difficultyWanted
//                setHPonDifficulty.putExtra("difficultyWanted", difficulty);

            }
        });
*/

        //---------------------------------------------------------------------

        Button configContinue = findViewById(R.id.configContinue);
        configContinue.setOnClickListener(v -> {
            //this text view is for debugging for now

            //alert for not having one of the elements selected
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please input a username, select a character, and select a difficulty")
                    .setTitle("");

            // Add buttons and their associated actions
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    // Perform any actions here
                }
            });

            // Create the AlertDialog and show it
            AlertDialog dialog = builder.create();

            if (!difficultyIsSelected || username[0].equals("") || !charSelected[0]) {
                dialog.show();
            } else {
                Intent game = new Intent(ConfigScreen.this, GameScreen.class);
                game.putExtra("charSelected", charIdentifierSelected[0]);
                game.putExtra("difficultyWanted", difficultySelected[0]);
                game.putExtra("username", username[0]);
                startActivity(game);
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        int radioDifficultyID = difficulties.getCheckedRadioButtonId();
        difficultySelectedButton = findViewById(radioDifficultyID);

        Toast.makeText(this, "Selected Difficulty: " + difficultySelectedButton.getText(), Toast.LENGTH_SHORT).show();
    }
}