package com.example.shakars_character_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditCharacter_activity extends AppCompatActivity implements View.OnClickListener {
    Button save;
    ImageButton settings;
    TextView title;
    EditText[][] editText;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String documentID;
    private static final String TAG = "ViewCharacterActivity";
    DocumentReference characterRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_character);

        Intent intent = getIntent();
        documentID = intent.getStringExtra("DocumentID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        characterRef = db.collection("users").document("test")
                .collection("characters").document(documentID);

        title = findViewById(R.id.editCharacterTitle);

        save = findViewById(R.id.editCharacterSaveButton);
        save.setOnClickListener(this);

        settings = findViewById(R.id.editOverlayButton);
        settings.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.editCharacterLayout);
        content.removeAllViews();

        editText = new EditText[CategoriesAndProperties.dataPC.categoriesPC.length][];

        for (int catIndex = 0; catIndex < CategoriesAndProperties.dataPC.categoriesPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title, content, false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll);
            cat_title_ll.removeAllViews();
            TextView catTitleTv = catRoot.findViewById(R.id.categoriesTV);
            catTitleTv.setText(CategoriesAndProperties.dataPC.categoriesPC[catIndex]);
            catTitleTv.setOnClickListener(this);
            catTitleTv.setTag(catIndex);
            content.addView(catRoot);
            categoriesLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CategoriesAndProperties.foldedPC[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CategoriesAndProperties.dataPC.propertiesPC[catIndex];
            editText[catIndex] = new EditText[props.length];
            int[] hints = CategoriesAndProperties.dataPC.hintsPC[catIndex];
            for (int propIndex = 0; propIndex < props.length; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property, content, false);
                TextView titleTV = root.findViewById(R.id.propertiesTV);
                titleTV.setText(props[propIndex]);

                int hintIndex = propIndex;
                if (hintIndex >= hints.length) {
                    System.err.println("Missing hint for " + catIndex + "+" + propIndex);
                    hintIndex = 0;

                }

                EditText hintTV = root.findViewById(R.id.hints);
                editText[catIndex][propIndex] = hintTV;
                hintTV.setHint(hints[hintIndex]);
                hintTV.setTag(hintIndex);

                cat_title_ll.addView(root);
            }

            getCharacter();

        }

    }

    @Override
    public void onClick(View v) {
        if (save.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            sendCharacter();
            if (TextUtils.equals(documentID,"")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("End character creation.");
                builder.setMessage("You are about to end character editing.\nThe character cannot be saved without a name.\nYou will be sent back to the front page.\nAre you sure you want to finish?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(EditCharacter_activity.this, AllCharacters_activity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            } else {
                Intent intent = new Intent(v.getContext(), ViewCharacter_activity.class);
                intent.putExtra("DocumentID", documentID);
                v.getContext().startActivity(intent);

            }
        }

        Object tag = v.getTag();
        if (tag != null) {
            int catIndex = (int) tag;
            CategoriesAndProperties.foldedPC[catIndex] = !CategoriesAndProperties.foldedPC[catIndex];

            View cat = categoriesLL.get(catIndex);
            if (CategoriesAndProperties.foldedPC[catIndex]) {
                cat.animate().scaleY(0);

            } else {
                cat.setVisibility(View.VISIBLE);
                cat.animate().scaleY(1);

            }

        }


    }

    ArrayList<View> categoriesLL = new ArrayList<>();

    private void getCharacter() {

        //Basic
        final EditText nameTV = editText[0][0];
        final EditText titleTV = editText[0][1];
        final EditText raceTV = editText[0][2];
        final EditText genderTV = editText[0][3];
        final EditText ageTV = editText[0][4];
        final EditText nationalityTV = editText[0][5];
        final EditText hometownTV = editText[0][6];
        final EditText continentTV = editText[0][7];
        final EditText rpClassTV = editText[0][8];
        final EditText sexualityTV = editText[0][9];
        final EditText attractedTV = editText[0][10];
        final EditText occupationTV = editText[0][11];
        final EditText religionTV = editText[0][12];
        //Looks
        final EditText eyesTV = editText[1][0];
        final EditText skinTV = editText[1][1];
        final EditText hairTV = editText[1][2];
        final EditText scentTV = editText[1][3];
        final EditText voiceTV = editText[1][4];
        final EditText featuresTV = editText[1][5];
        //Personality
        final EditText personalityTV = editText[2][0];
        final EditText behaviourTV = editText[2][1];
        final EditText traitsTV = editText[2][2];
        final EditText flawsTV = editText[2][3];
        final EditText quirksTV = editText[2][4];
        final EditText hobbiesTV = editText[2][5];
        final EditText strengthsTV = editText[2][6];
        final EditText wantsTV = editText[2][7];
        final EditText fearsTV = editText[2][8];
        final EditText secretsTV = editText[2][9];
        //Relations
        final EditText familyTV = editText[3][0];
        final EditText alliesTV = editText[3][1];
        final EditText enemiesTV = editText[3][2];
        final EditText groupsTV = editText[3][3];
        //Goals and BG
        final EditText goalsTV = editText[4][0];
        final EditText backGroundTV = editText[4][1];


        characterRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot character = task.getResult();
                    assert character != null;
                    if (character.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + character.getData());
                        String name = character.getString("Name");
                        String title = character.getString("TitleKenningMoniker");
                        String race = character.getString("Race");
                        String gender = character.getString("Gender");
                        String age = character.getString("Age");
                        String nationality = character.getString("Nationality");
                        String hometown = character.getString("Hometown");
                        String continent = character.getString("Continent");
                        String rpClass = character.getString("Class");
                        String sexuality = character.getString("Sexuality");
                        String attracted = character.getString("TraitsAttractedTo");
                        String occupation = character.getString("Occupation");
                        String religion = character.getString("Religion");
                        //Looks
                        String eyes = character.getString("Eyes");
                        String skin = character.getString("Skin");
                        String hair = character.getString("Hair");
                        String scent = character.getString("Scent");
                        String voice = character.getString("Voice");
                        String features = character.getString("CharacteristicFeatures");
                        //Personality
                        String personality = character.getString("Personality");
                        String behaviour = character.getString("BehaviourAndManner");
                        String traits = character.getString("CharacterTraits");
                        String flaws = character.getString("CharacterFlaws");
                        String quirks = character.getString("Quirks");
                        String hobbies = character.getString("Hobbies");
                        String strengths = character.getString("StrengthsAndWeaknesses");
                        String wants = character.getString("WantsAndDesires");
                        String fears = character.getString("FearsAndInsecurities");
                        String secrets = character.getString("Secrets");
                        //Relations
                        String family = character.getString("Family");
                        String allies = character.getString("AlliesAndContacts");
                        String enemies = character.getString("Enemies");
                        String groups = character.getString("AffiliatedGroups");
                        //Goals and BG
                        String goals = character.getString("CurrentGoals");
                        String backGround = character.getString("BackgroundStory");

                        nameTV.setText(name);
                        titleTV.setText(title);
                        raceTV.setText(race);
                        genderTV.setText(gender);
                        ageTV.setText(age);
                        nationalityTV.setText(nationality);
                        hometownTV.setText(hometown);
                        continentTV.setText(continent);
                        rpClassTV.setText(rpClass);
                        sexualityTV.setText(sexuality);
                        attractedTV.setText(attracted);
                        occupationTV.setText(occupation);
                        religionTV.setText(religion);
                        eyesTV.setText(eyes);
                        skinTV.setText(skin);
                        hairTV.setText(hair);
                        scentTV.setText(scent);
                        voiceTV.setText(voice);
                        featuresTV.setText(features);
                        personalityTV.setText(personality);
                        behaviourTV.setText(behaviour);
                        traitsTV.setText(traits);
                        flawsTV.setText(flaws);
                        quirksTV.setText(quirks);
                        hobbiesTV.setText(hobbies);
                        strengthsTV.setText(strengths);
                        wantsTV.setText(wants);
                        fearsTV.setText(fears);
                        secretsTV.setText(secrets);
                        familyTV.setText(family);
                        alliesTV.setText(allies);
                        enemiesTV.setText(enemies);
                        groupsTV.setText(groups);
                        goalsTV.setText(goals);
                        backGroundTV.setText(backGround);


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }

    private void sendCharacter() {
        //Basic
        String name = editText[0][0].getText().toString();
        String title = editText[0][1].getText().toString();
        String race = editText[0][2].getText().toString();
        String gender = editText[0][3].getText().toString();
        String age = editText[0][4].getText().toString();
        String nationality = editText[0][5].getText().toString();
        String hometown = editText[0][6].getText().toString();
        String continent = editText[0][7].getText().toString();
        String rpClass = editText[0][8].getText().toString();
        String sexuality = editText[0][9].getText().toString();
        String attracted = editText[0][10].getText().toString();
        String occupation = editText[0][11].getText().toString();
        String religion = editText[0][12].getText().toString();
        //Looks
        String eyes = editText[1][0].getText().toString();
        String skin = editText[1][1].getText().toString();
        String hair = editText[1][2].getText().toString();
        String scent = editText[1][3].getText().toString();
        String voice = editText[1][4].getText().toString();
        String features = editText[1][5].getText().toString();
        //Personality
        String personality = editText[2][0].getText().toString();
        String behaviour = editText[2][1].getText().toString();
        String traits = editText[2][2].getText().toString();
        String flaws = editText[2][3].getText().toString();
        String quirks = editText[2][4].getText().toString();
        String hobbies = editText[2][5].getText().toString();
        String strengths = editText[2][6].getText().toString();
        String wants = editText[2][7].getText().toString();
        String fears = editText[2][8].getText().toString();
        String secrets = editText[2][9].getText().toString();
        //Relations
        String family = editText[3][0].getText().toString();
        String allies = editText[3][1].getText().toString();
        String enemies = editText[3][2].getText().toString();
        String groups = editText[3][3].getText().toString();
        //Goals and BG
        String goals = editText[4][0].getText().toString();
        String backGround = editText[4][1].getText().toString();

        documentID = name;

        Map<String, Object> character = new HashMap<>();
        character.put("Name", name);
        character.put("TitleKenningMoniker", title);
        character.put("Race", race);
        character.put("Gender", gender);
        character.put("Age", age);
        character.put("Nationality", nationality);
        character.put("Hometown", hometown);
        character.put("Continent", continent);
        character.put("Class", rpClass);
        character.put("Sexuality", sexuality);
        character.put("TraitsAttractedTo", attracted);
        character.put("Occupation", occupation);
        character.put("Religion", religion);
        character.put("Eyes", eyes);
        character.put("Skin", skin);
        character.put("Hair", hair);
        character.put("Scent", scent);
        character.put("Voice", voice);
        character.put("CharacteristicFeatures", features);
        character.put("Personality", personality);
        character.put("BehaviourAndManner", behaviour);
        character.put("CharacterTraits", traits);
        character.put("CharacterFlaws",flaws);
        character.put("Quirks", quirks);
        character.put("Hobbies", hobbies);
        character.put("StrengthsAndWeaknesses", strengths);
        character.put("WantsAndDesires", wants);
        character.put("FearsAndInsecurities", fears);
        character.put("Secrets", secrets);
        character.put("Family", family);
        character.put("AlliesAndContacts", allies);
        character.put("Enemies", enemies);
        character.put("AffiliatedGroups", groups);
        character.put("CurrentGoals", goals);
        character.put("BackgroundStory", backGround);

        FirebaseFirestore db = FirebaseFirestore.getInstance();



        DocumentReference newCharacterRef = db.collection("users").document("test")
                .collection("characters").document(name);


        newCharacterRef.set(character).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

}
