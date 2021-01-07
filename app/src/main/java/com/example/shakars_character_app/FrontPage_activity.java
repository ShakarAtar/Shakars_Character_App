package com.example.shakars_character_app;

import android.content.Intent;
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
        if (characters.getId() == v.getId() || charactersTVClick.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(), AllCharacters_activity.class);
            v.getContext().startActivity(intent);

        } else if (npcs.getId() == v.getId() || npcsTVClick.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(), AllNPCS_activity.class);
            v.getContext().startActivity(intent);

        } else if (notes.getId() == v.getId() || notesTVClick.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),AllNotes_activity.class);
            v.getContext().startActivity(intent);

        } else if (settings.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),Setting_activity.class);
            v.getContext().startActivity(intent);
        }

    }
}
