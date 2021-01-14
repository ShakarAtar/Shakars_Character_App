package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrontPage_activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton characters, npcs, notes, settings;
    TextView title, charactersTVClick, npcsTVClick, notesTVClick;
    private static final String TAG = "FrontPage";
    FirebaseFirestore db;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        CollectionReference cities = db.collection("cities");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "San Francisco");
        data1.put("state", "CA");
        data1.put("country", "USA");
        data1.put("capital", false);
        data1.put("population", 860000);
        data1.put("regions", Arrays.asList("west_coast", "norcal"));
        cities.document("SF").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("name", "Los Angeles");
        data2.put("state", "CA");
        data2.put("country", "USA");
        data2.put("capital", false);
        data2.put("population", 3900000);
        data2.put("regions", Arrays.asList("west_coast", "socal"));
        cities.document("LA").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("name", "Washington D.C.");
        data3.put("state", null);
        data3.put("country", "USA");
        data3.put("capital", true);
        data3.put("population", 680000);
        data3.put("regions", Arrays.asList("east_coast"));
        cities.document("DC").set(data3);

        Map<String, Object> data4 = new HashMap<>();
        data4.put("name", "Tokyo");
        data4.put("state", null);
        data4.put("country", "Japan");
        data4.put("capital", true);
        data4.put("population", 9000000);
        data4.put("regions", Arrays.asList("kanto", "honshu"));
        cities.document("TOK").set(data4);

        Map<String, Object> data5 = new HashMap<>();
        data5.put("name", "Beijing");
        data5.put("state", null);
        data5.put("country", "China");
        data5.put("capital", true);
        data5.put("population", 21500000);
        data5.put("regions", Arrays.asList("jingjinji", "hebei"));
        cities.document("BJ").set(data5);


        DocumentReference docRef = db.collection("cities").document("SF");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (characters.getId() == v.getId() || charactersTVClick.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(), AllCharacters_activity.class);
            v.getContext().startActivity(intent);

        } else if (npcs.getId() == v.getId() || npcsTVClick.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(), AllNPCS_activity.class);
            v.getContext().startActivity(intent);

        } else if (notes.getId() == v.getId() || notesTVClick.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),AllNotes_activity.class);
            v.getContext().startActivity(intent);

        } else if (settings.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),Setting_activity.class);
            v.getContext().startActivity(intent);
        }

    }
}
