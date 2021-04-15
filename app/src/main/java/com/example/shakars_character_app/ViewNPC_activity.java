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
import android.widget.LinearLayout;;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewNPC_activity extends AppCompatActivity implements View.OnClickListener {
    Button edit;
    ImageButton settings;
    TextView title;
    TextView[][] viewText;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String documentID;
    private static final String TAG = "ViewNPCActivity";
    DocumentReference npcRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_npc);
        Intent intent = getIntent();
        documentID = intent.getStringExtra("DocumentID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        npcRef = db.collection("users").document("test")
                .collection("npcs").document(documentID);

        title = findViewById(R.id.viewNPCTitle);

        edit = findViewById(R.id.viewNPCEditButton);
        edit.setOnClickListener(this);

        settings = findViewById(R.id.viewNPCOverlayButton);
        settings.setOnClickListener(this);
        settings.setVisibility(View.INVISIBLE);

        ViewGroup content = findViewById(R.id.viewNPCLayout);
        content.removeAllViews();

        viewText = new TextView[CategoriesAndProperties.dataNPC.categoriesNPC.length][];

        for (int catIndex = 0; catIndex < CategoriesAndProperties.dataNPC.categoriesNPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title_view,content,false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll_view);
            cat_title_ll.removeAllViews();
            TextView catTitleTV = catRoot.findViewById(R.id.viewCategoriesTV);
            catTitleTV.setText(CategoriesAndProperties.dataNPC.categoriesNPC[catIndex]);
            catTitleTV.setOnClickListener(this);
            catTitleTV.setTag(catIndex);
            content.addView(catRoot);
            categoriesLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CategoriesAndProperties.foldedNPC[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CategoriesAndProperties.dataNPC.propertiesNPC[catIndex];
            viewText[catIndex] = new TextView[props.length];
            int[] hints = CategoriesAndProperties.dataNPC.hintsNPC[catIndex];
            for (int propIndex = 0; propIndex < props.length; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property_view, content, false);
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

        getNPC();


    }


    @Override
    public void onClick(View v) {
        if (edit.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),EditNPC_activity.class);
            intent.putExtra("DocumentID", documentID);
            v.getContext().startActivity(intent);
        }

        Object tag = v.getTag();
        if (tag != null) {
            int catIndex = (int) tag;
            CategoriesAndProperties.foldedNPC[catIndex] = !CategoriesAndProperties.foldedNPC[catIndex];

            View cat = categoriesLL.get(catIndex);
            if (CategoriesAndProperties.foldedNPC[catIndex]) {
                cat.animate().scaleY(0);

            } else {
                cat.setVisibility(View.VISIBLE);
                cat.animate().scaleY(1);

            }

        }






    }

    public void onBackPressed() {
        Intent intent = new Intent(ViewNPC_activity.this, AllNPCS_activity.class);
        startActivity(intent);
    }

    ArrayList<View> categoriesLL = new ArrayList<>();

    private void getNPC() {

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
        //NPC Only
        final TextView abilitiesTV = viewText[5][0];
        final TextView partTV = viewText[5][1];
        final TextView hooksTV = viewText[5][2];
        final TextView plotTV = viewText[5][3];
        final TextView motivationsTV = viewText[5][4];

        npcRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot npc = task.getResult();
                    assert npc != null;
                    if (npc.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + npc.getData());
                        String name = npc.getString("Name");
                        String title = npc.getString("TitleKenningMoniker");
                        String race = npc.getString("Race");
                        String gender = npc.getString("Gender");
                        String age = npc.getString("Age");
                        String nationality = npc.getString("Nationality");
                        String hometown = npc.getString("Hometown");
                        String continent = npc.getString("Continent");
                        String rpClass = npc.getString("Class");
                        String sexuality = npc.getString("Sexuality");
                        String attracted = npc.getString("TraitsAttractedTo");
                        String occupation = npc.getString("Occupation");
                        String religion = npc.getString("Religion");
                        //Looks
                        String eyes = npc.getString("Eyes");
                        String skin = npc.getString("Skin");
                        String hair = npc.getString("Hair");
                        String scent = npc.getString("Scent");
                        String voice = npc.getString("Voice");
                        String features = npc.getString("CharacteristicFeatures");
                        //Personality
                        String personality = npc.getString("Personality");
                        String behaviour = npc.getString("BehaviourAndManner");
                        String traits = npc.getString("CharacterTraits");
                        String flaws = npc.getString("CharacterFlaws");
                        String quirks = npc.getString("Quirks");
                        String hobbies = npc.getString("Hobbies");
                        String strengths = npc.getString("StrengthsAndWeaknesses");
                        String wants = npc.getString("WantsAndDesires");
                        String fears = npc.getString("FearsAndInsecurities");
                        String secrets = npc.getString("Secrets");
                        //Relations
                        String family = npc.getString("Family");
                        String allies = npc.getString("AlliesAndContacts");
                        String enemies = npc.getString("Enemies");
                        String groups = npc.getString("AffiliatedGroups");
                        //Goals and BG
                        String goals = npc.getString("CurrentGoals");
                        String backGround = npc.getString("BackgroundStory");
                        //NPC Only
                        String abilities = npc.getString("SpecialAbilities");
                        String part = npc.getString("PartOfTheWorld");
                        String hooks = npc.getString("HooksToPlayers");
                        String plot = npc.getString("PlotHooks");
                        String motivations = npc.getString("Motivations");

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
                        abilitiesTV.setText(abilities);
                        partTV.setText(part);
                        hooksTV.setText(hooks);
                        plotTV.setText(plot);
                        motivationsTV.setText(motivations);

                        documentID = name;


                    } else  {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }



}
