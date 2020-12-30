package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewNPC extends AppCompatActivity implements View.OnClickListener {
    Button save;
    ImageButton settings;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        save = findViewById(R.id.newNPCSaveButton);
        save.setOnClickListener(this);

        settings = findViewById(R.id.newNPCOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.newNPCTitle);
        title.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

    }
}
