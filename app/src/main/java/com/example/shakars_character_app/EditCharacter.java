package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditCharacter extends AppCompatActivity implements View.OnClickListener {
    Button save, settings;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_character);

        title.findViewById(R.id.editCharacterTitle);

        save.findViewById(R.id.editCharacterSaveButton);
        save.setOnClickListener(this);

        settings.findViewById(R.id.editOverlayButton);
        settings.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.editCharacterLayout);
        content.removeAllViews();



    }

    @Override
    public void onClick(View v) {

    }
}
