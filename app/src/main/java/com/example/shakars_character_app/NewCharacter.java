package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewCharacter extends AppCompatActivity implements View.OnClickListener {
    Button save;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_character);

        //Buttons
        save = findViewById(R.id.newCharacterSaveButton);
        save.setOnClickListener(this);

        title = findViewById(R.id.newCharacterTitle);
        title.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.newCharacterLayout);

        for (int i = 0; i < titles.length; i++) {
            View root = getLayoutInflater().inflate(R.layout.new_character_basic_info, content, false);
            root.findViewById()

            content.addView(root);
            }


        }

        String[] titles = {"@string/name", "@string/titleKenningMoniker", "@string/race", "@string/gender", "@string/age", "@string/nationality", "@string/hometown", "@string/continent", "@string/rpClass", "@string/sexuality", "@string/occupation", "@string/religion"};
        String[] hints = {"@string/nameSuggestion", "@string/titleKenningMonikerSuggestion", "@string/raceSuggestion", "@string/genderSuggestion", "@string/ageSuggestion", "@string/nationalitySuggestion", "@string/hometownSuggestion", "@string/continentSuggestion", "@string/rpClassSuggestion", "@string/sexualitySuggestion", "@string/occupationSuggestion", "@string/religionSuggestion"};
        ArrayList<View> titlesTv = new ArrayList<>();

        @Override
        public void onClick (View v){

        }
    }

