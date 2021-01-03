package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AllNotes_activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton settings;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = findViewById(R.id.notesOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.notesTitle);
    }

    @Override
    public void onClick(View v) {

    }
}
