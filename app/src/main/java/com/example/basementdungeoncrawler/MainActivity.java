package com.example.basementdungeoncrawler;

import android.content.DialogInterface;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        char1.setOnClickListener(a -> {
            charText.setText("Character 1 selected");
            charSelected[0] = true;
        });

        char2.setOnClickListener(b -> {
            charText.setText("Character 2 selected");
            charSelected[0] = true;
        });

        char3.setOnClickListener(c -> {
            charText.setText("Character 3 selected");
            charSelected[0] = true;
        });

        //---------------------------------------------------------------------

        //difficulty config
        RadioGroup difficulties = findViewById(R.id.difficultyRadioGroup);
        final boolean[] difficultySelected = {false};
        difficulties.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                double difficulty = 1;
                String difficultyId = String.valueOf(difficulties.getCheckedRadioButtonId());
                String difficultyText;


                if (difficultyId.equals("2131230859")) {
                    difficulty = 2;
                    difficultySelected[0] = true;
                } else if (difficultyId.equals("2131230860")) {
                    difficulty = 1.5;
                    difficultySelected[0] = true;
                } else {
                    difficulty = 1;
                    difficultySelected[0] = true;
                }

                if (difficultyId.equals("-1")) {
                    difficultyText = "No difficulty selected";
                    difficultySelected[0] = false;
                } else {
                    difficultyText = String.format("Difficulty : %.1f", difficulty);
                }

                TextView difficultyLevelText = findViewById(R.id.difficultyLevel);
                difficultyLevelText.setText(difficultyText);
            }
        });

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

            if (!difficultySelected[0] || username[0].equals("") || !charSelected[0]) {
                dialog.show();
            }
        });
    }
}
