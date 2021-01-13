package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AllNPCS_activity extends AppCompatActivity implements View.OnClickListener {

    Button newNPCButton;
    ImageButton settings;
    TextView newNPCTV, title;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npc_list);

        newNPCButton = findViewById(R.id.NPCListCreateNewNPCButton);
        newNPCButton.setOnClickListener(this);

        settings = findViewById(R.id.NPCListOverlayButton);
        settings.setOnClickListener(this);

        newNPCTV = findViewById(R.id.NPCListCreateNewNPCTV);
        newNPCTV.setOnClickListener(this);

        title = findViewById(R.id.NPCListTitle);

    }

    @Override
    public void onClick(View v) {
        if (newNPCButton.getId() == v.getId() || newNPCTV.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),NewNPC_activity.class);
            v.getContext().startActivity(intent);

        }

    }
}
