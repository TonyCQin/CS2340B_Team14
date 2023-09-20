package com.example.basementdungeoncrawler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button char1 = findViewById(R.id.char1);
        Button char2 = findViewById(R.id.char2);
        Button char3 = findViewById(R.id.char3);
        TextView charText = findViewById(R.id.charTextView);

        char1.setOnClickListener(v -> {
            charText.setText("Character 1 selected");
        });

        char2.setOnClickListener(v -> {
            charText.setText("Character 2 selected");
        });

        char3.setOnClickListener(v -> {
            charText.setText("Character 3 selected");
        });

        RadioGroup difficulties = findViewById(R.id.difficultyRadioGroup);
        difficulties.setOnClickListener(v -> {
            double difficulty = 1;
        });
    }

    public void onSubmitClick(View view) {
        String inputText = editText.getText().toString().trim();

        if (!inputText.isEmpty()) {
            Toast.makeText(this, "You entered: " + inputText, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }
}
