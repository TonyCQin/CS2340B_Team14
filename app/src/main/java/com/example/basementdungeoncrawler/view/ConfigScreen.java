package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.viewModel.GameViewModel;
import com.example.basementdungeoncrawler.viewModel.PlayerViewModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ConfigScreen extends AppCompatActivity {
    private TextView difficultyLevelText;
    private String difficultyText;
    private Boolean difficultyIsSelected = false;
    private PlayerViewModel playerViewModel;
    private GameViewModel gameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);

        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        ImageView sprite1 = findViewById(R.id.sprite1);
        ImageView sprite2 = findViewById(R.id.sprite2);
        ImageView sprite3 = findViewById(R.id.sprite3);

        sprite1.setImageResource(R.drawable.knight_sheet);
        sprite2.setImageResource(R.drawable.rogue_sheet);
        sprite3.setImageResource(R.drawable.wizard_sheet);

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
        });

        char2.setOnClickListener(b -> {
            charText.setText("Character 2 selected");
            charSelected[0] = true;
            charIdentifierSelected[0] = 2;
        });

        char3.setOnClickListener(c -> {
            charText.setText("Character 3 selected");
            charSelected[0] = true;
            charIdentifierSelected[0] = 3;
        });

        //---------------------------------------------------------------------

        // difficulty config
        RadioGroup difficulties = findViewById(R.id.difficultyRadioGroup);
        difficultyLevelText = findViewById(R.id.difficultyLevel);
        final int[] difficultySelected = {0};

        difficulties.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                TextView difficultyText = findViewById(R.id.difficultyText);

                if (selectedRadioButton == null) {
                    ConfigScreen.this.difficultyText = "No Difficulty Selected";
                    difficultyIsSelected = false;
                    difficultySelected[0] = 0;
                    difficultyText.setText("0");
                } else {
                    // Get the text from the selected radio button
                    String selectedDifficultyText = selectedRadioButton.getText().toString();

                    // Based on selected text, set difficultyIsSelected and difficultySelected
                    if (selectedDifficultyText.equals("Easy")) {
                        difficultyIsSelected = true;
                        difficultySelected[0] = 1;
                        difficultyText.setText("1");
                    } else if (selectedDifficultyText.equals("Medium")) {
                        difficultyIsSelected = true;
                        difficultySelected[0] = 2;
                        difficultyText.setText("2");
                    } else if (selectedDifficultyText.equals("Hard")) {
                        difficultyIsSelected = true;
                        difficultySelected[0] = 3;
                        difficultyText.setText("3");
                    } else {
                        ConfigScreen.this.difficultyText = "No Difficulty Selected";
                        difficultyIsSelected = false;
                        difficultySelected[0] = 0;
                        difficultyText.setText("0");
                    }
                }
                difficultyLevelText.setText(ConfigScreen.this.difficultyText);
            }
        });

        //---------------------------------------------------------------------

        Button configContinue = findViewById(R.id.configContinue);
        configContinue.setOnClickListener(v -> {
            // This text view is for debugging for now

            // Alert for not having one of the elements selected
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please input a username, select a character, "
                            + "and select a difficulty")
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

            if (!difficultyIsSelected || username[0].equals("") || username == null
                || isOnlyWhitespace(username[0]) || !charSelected[0]) {
                dialog.show();
            } else {
                playerViewModel.setUsername(username[0]);
                playerViewModel.setSprite(charIdentifierSelected[0]);
                gameViewModel.setDifficulty(difficultySelected[0]);
                Intent game = new Intent(ConfigScreen.this, GameScreen.class);
                startActivity(game);
                finish();
            }
        });
    }
    public static boolean isOnlyWhitespace(String n) {
        for (int i = 0; i < n.length(); i++) {
            if (!Character.isWhitespace(n.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}