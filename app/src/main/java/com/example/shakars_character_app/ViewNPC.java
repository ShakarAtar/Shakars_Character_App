package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewNPC extends AppCompatActivity implements View.OnClickListener {
    Button edit;
    ImageButton settings;
    TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edit = findViewById(R.id.viewNPCEditButton);
        edit.setOnClickListener(this);

        settings = findViewById(R.id.viewNPCOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.viewNPCTitle);
        edit.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {

    }
}
