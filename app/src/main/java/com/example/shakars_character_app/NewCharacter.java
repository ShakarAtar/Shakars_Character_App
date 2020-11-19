package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewCharacter extends AppCompatActivity implements View.OnClickListener {
    Button save;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_character);

        //Buttons
        save = findViewById(R.id.newCharacterSaveButton);
        save.setOnClickListener(this);

        title = findViewById(R.id.newCharacterTitle);
        title.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
