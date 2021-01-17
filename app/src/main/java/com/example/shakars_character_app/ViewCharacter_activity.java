package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewCharacter_activity extends AppCompatActivity implements View.OnClickListener {
    Button edit;
    ImageButton settings;
    TextView title;
    TextView[][] viewText;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String documentID;
    private static final String TAG = "ViewCharacterActivity";
    DocumentReference characterRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_character);
        Intent intent = getIntent();
        documentID = intent.getStringExtra("DocumentID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        characterRef = db.collection("users").document("test")
                .collection("characters").document(documentID);

        title = findViewById(R.id.viewCharacterTitle);

        edit = findViewById(R.id.viewCharacterEditButton);
        edit.setOnClickListener(this);

        settings = findViewById(R.id.viewCharacterOverlayButton);
        //        settings.setOnClickListener(this);
        settings.setVisibility(View.INVISIBLE);

        ViewGroup content = findViewById(R.id.viewCharacterLayout);
        content.removeAllViews();

        viewText = new TextView[CategoriesAndProperties.dataPC.categoriesPC.length][];

        for (int catIndex = 0; catIndex < CategoriesAndProperties.dataPC.categoriesPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title_view,content,false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll_view);
            cat_title_ll.removeAllViews();
            TextView catTitleTV = catRoot.findViewById(R.id.viewCategoriesTV);
            catTitleTV.setText(CategoriesAndProperties.dataPC.categoriesPC[catIndex]);
            catTitleTV.setOnClickListener(this);
            catTitleTV.setTag(catIndex);
            content.addView(catRoot);
            categoriesLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CategoriesAndProperties.foldedPC[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CategoriesAndProperties.dataPC.propertiesPC[catIndex];
            viewText[catIndex] = new TextView[props.length];
            int [] hints = CategoriesAndProperties.dataPC.hintsPC[catIndex];
            for (int propIndex = 0; propIndex < props.length ; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property_view,content,false);
                TextView titleTV = root.findViewById(R.id.viewPropertiesTV);
                titleTV.setText(props[propIndex]);

                int hintIndex = propIndex;
                if (hintIndex >= hints.length) {
                    System.err.println("Missing hint for"+catIndex+"+"+propIndex);
                    hintIndex = 0;
                }
                TextView hintTV = root.findViewById(R.id.viewHints);
                viewText[catIndex][propIndex] = hintTV;
                hintTV.setHint(hints[hintIndex]);
                hintTV.setTag(hintIndex);

                cat_title_ll.addView(root);

            }

        }

        getCharacter();

    }

    @Override
    public void onClick(View v) {
        if (edit.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),EditCharacter_activity.class);
            intent.putExtra("DocumentID", documentID);
            v.getContext().startActivity(intent);
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

    public void onBackPressed() {
        Intent intent = new Intent(ViewCharacter_activity.this, AllCharacters_activity.class);
        startActivity(intent);
    }

    private void getCharacter() {

        //Basic
        final TextView nameTV = viewText[0][0];
        final TextView titleTV = viewText[0][1];
        final TextView raceTV = viewText[0][2];
        final TextView genderTV = viewText[0][3];
        final TextView ageTV = viewText[0][4];
        final TextView nationalityTV = viewText[0][5];
        final TextView hometownTV = viewText[0][6];
        final TextView continentTV = viewText[0][7];
        final TextView rpClassTV = viewText[0][8];
        final TextView sexualityTV = viewText[0][9];
        final TextView attractedTV = viewText[0][10];
        final TextView occupationTV = viewText[0][11];
        final TextView religionTV = viewText[0][12];
        //Looks
        final TextView eyesTV = viewText[1][0];
        final TextView skinTV = viewText[1][1];
        final TextView hairTV = viewText[1][2];
        final TextView scentTV = viewText[1][3];
        final TextView voiceTV = viewText[1][4];
        final TextView featuresTV = viewText[1][5];
        //Personality
        final TextView personalityTV = viewText[2][0];
        final TextView behaviourTV = viewText[2][1];
        final TextView traitsTV = viewText[2][2];
        final TextView flawsTV = viewText[2][3];
        final TextView quirksTV = viewText[2][4];
        final TextView hobbiesTV = viewText[2][5];
        final TextView strengthsTV = viewText[2][6];
        final TextView wantsTV = viewText[2][7];
        final TextView fearsTV = viewText[2][8];
        final TextView secretsTV = viewText[2][9];
        //Relations
        final TextView familyTV = viewText[3][0];
        final TextView alliesTV = viewText[3][1];
        final TextView enemiesTV = viewText[3][2];
        final TextView groupsTV = viewText[3][3];
        //Goals and BG
        final TextView goalsTV = viewText[4][0];
        final TextView backGroundTV = viewText[4][1];


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

                        documentID = name;


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });





    }

    ArrayList<View> categoriesLL = new ArrayList<>();
}
