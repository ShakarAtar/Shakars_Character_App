package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AllCharacters_activity extends AppCompatActivity implements View.OnClickListener {

    Button newCharButton;
    ImageButton settings;
    TextView newCharTV, title;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_list);

        newCharButton = findViewById(R.id.characterListCreateNewCharButton);
        newCharButton.setOnClickListener(this);

        settings = findViewById(R.id.characterListOverlayButton);
        settings.setOnClickListener(this);

        newCharTV = findViewById(R.id.characterListCreateNewCharacterTV);
        newCharTV.setOnClickListener(this);

        title = findViewById(R.id.characterListTitle);

    }

    @Override
    public void onClick(View v) {
        if (newCharButton.getId() == v.getId() || newCharTV.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(), NewCharacter_activity.class);
            v.getContext().startActivity(intent);

        }

    }
}