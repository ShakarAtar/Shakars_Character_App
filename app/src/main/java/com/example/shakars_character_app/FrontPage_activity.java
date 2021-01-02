package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FrontPage_activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton characters, npcs, notes, settings;
    TextView title, charactersTVClick, npcsTVClick, notesTVClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        title = findViewById(R.id.appTitleFrontPage);

        characters = findViewById(R.id.frontPageCharButton);
        characters.setOnClickListener(this);

        npcs = findViewById(R.id.frontPageNPCsButton);
        npcs.setOnClickListener(this);

        notes = findViewById(R.id.frontPageNotesButton);
        notes.setOnClickListener(this);

        settings = findViewById(R.id.frontPageOverlayButton);
        settings.setOnClickListener(this);

        charactersTVClick = findViewById(R.id.frontPageCharactersText);
        charactersTVClick.setOnClickListener(this);

        npcsTVClick = findViewById(R.id.frontPageNPCText);
        npcsTVClick.setOnClickListener(this);

        notesTVClick = findViewById(R.id.frontPageNotesText);
        notesTVClick.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

    }
}
