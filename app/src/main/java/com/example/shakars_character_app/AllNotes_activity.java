package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AllNotes_activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton settings;
    TextView title;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        settings = findViewById(R.id.notesListOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.notesListTitle);
    }

    @Override
    public void onClick(View v) {

    }
}
