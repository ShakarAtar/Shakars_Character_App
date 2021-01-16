package com.example.shakars_character_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.airbnb.lottie.LottieAnimationView;
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

public class EditNPC_activity extends AppCompatActivity implements View.OnClickListener {
    Button save;
    ImageButton settings;
    TextView title;
    EditText[][] editText;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String documentID;
    private static final String TAG = "EditNPCActivity";
    DocumentReference npcRef;
    LottieAnimationView loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_npc);

        Intent intent = getIntent();
        documentID = intent.getStringExtra("DocumentID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        npcRef = db.collection("users").document("test")
                .collection("npcs").document(documentID);

        title = findViewById(R.id.editNPCTitle);

        save = findViewById(R.id.editNPCSaveButton);
        save.setOnClickListener(this);

        settings = findViewById(R.id.editNPCOverlayButton);
        settings.setOnClickListener(this);

        loading = findViewById(R.id.editNPCLoadAnimation);
        loading.setVisibility(View.INVISIBLE);



        ViewGroup content = findViewById(R.id.editNPCLayout);
        content.removeAllViews();

        editText = new EditText[CategoriesAndProperties.dataNPC.categoriesNPC.length][];

        for (int catIndex = 0; catIndex < CategoriesAndProperties.dataNPC.categoriesNPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title,content,false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll);
            cat_title_ll.removeAllViews();
            TextView catTitleTV = catRoot.findViewById(R.id.categoriesTV);
            catTitleTV.setText(CategoriesAndProperties.dataNPC.categoriesNPC[catIndex]);
            catTitleTV.setOnClickListener(this);
            catTitleTV.setTag(catIndex);
            content.addView(catRoot);
            categoriesLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CategoriesAndProperties.foldedNPC[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CategoriesAndProperties.dataNPC.propertiesNPC[catIndex];
            editText[catIndex] = new EditText[props.length];
            int[] hints = CategoriesAndProperties.dataNPC.hintsNPC[catIndex];
            for (int propIndex = 0; propIndex < props.length; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property,content,false);
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
                hintTV.setTag(hints[hintIndex]);

                cat_title_ll.addView(root);
            }

            getNPC();

        }


    }

    @Override
    public void onClick(final View v) {
        if (save.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            sendNPC();
            if (TextUtils.equals(documentID,"")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("End character creation.");
                builder.setMessage("You are about to end character editing.\nThe character cannot be saved without a name.\nYou will be sent back to the front page.\nAre you sure you want to finish?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(EditNPC_activity.this, AllNPCS_activity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            } else {
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                ViewGroup content = findViewById(R.id.editNPCLayout);
                content.setVisibility(View.INVISIBLE);
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(v.getContext(), ViewNPC_activity.class);
                        intent.putExtra("DocumentID", documentID);
                        v.getContext().startActivity(intent);

                    }
                }.start();

            }
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

    ArrayList<View> categoriesLL = new ArrayList<>();

    private void getNPC() {

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
        //NPC Only
        final EditText abilitiesTV = editText[5][0];
        final EditText partTV = editText[5][1];
        final EditText hooksTV = editText[5][2];
        final EditText plotTV = editText[5][3];
        final EditText motivationsTV = editText[5][4];

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
                        String traits = npc.getString("npcTraits");
                        String flaws = npc.getString("npcFlaws");
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

                    } else  {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }

    private void sendNPC() {
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
        //NPC Only
        String abilities = editText[5][0].getText().toString();
        String part = editText[5][1].getText().toString();
        String hooks = editText[5][2].getText().toString();
        String plot = editText[5][3].getText().toString();
        String motivations = editText[5][4].getText().toString();

        documentID = name;

        Map<String, Object> npc = new HashMap<>();
        npc.put("Name", name);
        npc.put("TitleKenningMoniker", title);
        npc.put("Race", race);
        npc.put("Gender", gender);
        npc.put("Age", age);
        npc.put("Nationality", nationality);
        npc.put("Hometown", hometown);
        npc.put("Continent", continent);
        npc.put("Class", rpClass);
        npc.put("Sexuality", sexuality);
        npc.put("TraitsAttractedTo", attracted);
        npc.put("Occupation", occupation);
        npc.put("Religion", religion);
        npc.put("Eyes", eyes);
        npc.put("Skin", skin);
        npc.put("Hair", hair);
        npc.put("Scent", scent);
        npc.put("Voice", voice);
        npc.put("CharacteristicFeatures", features);
        npc.put("Personality", personality);
        npc.put("BehaviourAndManner", behaviour);
        npc.put("CharacterTraits", traits);
        npc.put("CharacterFlaws",flaws);
        npc.put("Quirks", quirks);
        npc.put("Hobbies", hobbies);
        npc.put("StrengthsAndWeaknesses", strengths);
        npc.put("WantsAndDesires", wants);
        npc.put("FearsAndInsecurities", fears);
        npc.put("Secrets", secrets);
        npc.put("Family", family);
        npc.put("AlliesAndContacts", allies);
        npc.put("Enemies", enemies);
        npc.put("AffiliatedGroups", groups);
        npc.put("CurrentGoals", goals);
        npc.put("BackgroundStory", backGround);
        npc.put("SpecialAbilities", abilities);
        npc.put("PartOfTheWorld", part);
        npc.put("HooksToPlayers", hooks);
        npc.put("PlotHooks", plot);
        npc.put("Motivations", motivations);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (TextUtils.isEmpty(name)) {
            editText[0][0].setError("This cannot be empty");
            return;
        }



        DocumentReference newCharacterRef = db.collection("users").document("test")
                .collection("npcs").document(name);


        newCharacterRef.set(npc).addOnSuccessListener(new OnSuccessListener<Void>() {
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
