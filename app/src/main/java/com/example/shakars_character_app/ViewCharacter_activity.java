package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String documentID = getIntent().getStringExtra("Document_ID");
    private DocumentReference characterRef = FirebaseFirestore.getInstance().document(documentID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_character);

        title = findViewById(R.id.viewCharacterTitle);

        edit = findViewById(R.id.viewCharacterEditButton);
        edit.setOnClickListener(this);

        settings = findViewById(R.id.viewCharacterOverlayButton);
        settings.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.viewCharacterLayout);
        content.removeAllViews();

        viewText = new TextView[CategoriesAndProperties.dataPC.categoriesPC.length][];

        for (int catIndex = 0; catIndex < CategoriesAndProperties.dataPC.categoriesPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title_view,content,false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll_view);
            cat_title_ll.removeAllViews();
            TextView catTitleTv = catRoot.findViewById(R.id.viewCategoriesTV);
            catTitleTv.setOnClickListener(this);
            cat_title_ll.setTag(catIndex);
            content.addView(catRoot);
            categoriesLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CategoriesAndProperties.foldedPC[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CategoriesAndProperties.dataPC.propertiesPC[catIndex];
            viewText[catIndex] = new EditText[props.length];
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

    private void getCharacter() {

        //Basic
        final TextView nameTV = viewText[0][0];
        TextView titleTV = viewText[0][1];
        TextView raceTV = viewText[0][2];
        TextView genderTV = viewText[0][3];
        TextView ageTV = viewText[0][4];
        TextView nationalityTV = viewText[0][5];
        TextView hometownTV = viewText[0][6];
        TextView continentTV = viewText[0][7];
        TextView rpClassTV = viewText[0][8];
        TextView sexualityTV = viewText[0][9];
        TextView attractedTV = viewText[0][10];
        TextView occupationTV = viewText[0][11];
        TextView religionTV = viewText[0][12];
        //Looks
        TextView eyesTV = viewText[1][0];
        TextView skinTV = viewText[1][1];
        TextView hairTV = viewText[1][2];
        TextView scentTV = viewText[1][3];
        TextView voiceTV = viewText[1][4];
        TextView featuresTV = viewText[1][5];
        //Personality
        TextView personalityTV = viewText[2][0];
        TextView behaviourTV = viewText[2][1];
        TextView traitsTV = viewText[2][2];
        TextView flawsTV = viewText[2][3];
        TextView quirksTV = viewText[2][4];
        TextView hobbiesTV = viewText[2][5];
        TextView strengthsTV = viewText[2][6];
        TextView wantsTV = viewText[2][7];
        TextView fearsTV = viewText[2][8];
        TextView secretsTV = viewText[2][9];
        //Relations
        TextView familyTV = viewText[3][0];
        TextView alliesTV = viewText[3][1];
        TextView enemiesTV = viewText[3][2];
        TextView groupsTV = viewText[3][3];
        //Goals and BG
        TextView goalsTV = viewText[4][0];
        TextView backGroundTV = viewText[4][1];

        DocumentReference documentReference = db.document(documentID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot character = task.getResult();
                    if (character.exists()) {
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
                    }
                }
            }
        });




        //Basic
        String name = viewText[0][0].setText();
        String title = viewText[0][1].getText().toString();
        String race = viewText[0][2].getText().toString();
        String gender = viewText[0][3].getText().toString();
        String age = viewText[0][4].getText().toString();
        String nationality = viewText[0][5].getText().toString();
        String hometown = viewText[0][6].getText().toString();
        String continent = viewText[0][7].getText().toString();
        String rpClass = viewText[0][8].getText().toString();
        String sexuality = viewText[0][9].getText().toString();
        String attracted = viewText[0][10].getText().toString();
        String occupation = viewText[0][11].getText().toString();
        String religion = viewText[0][12].getText().toString();
        //Looks
        String eyes = viewText[1][0].getText().toString();
        String skin = viewText[1][1].getText().toString();
        String hair = viewText[1][2].getText().toString();
        String scent = viewText[1][3].getText().toString();
        String voice = viewText[1][4].getText().toString();
        String features = viewText[1][5].getText().toString();
        //Personality
        String personality = viewText[2][0].getText().toString();
        String behaviour = viewText[2][1].getText().toString();
        String traits = viewText[2][2].getText().toString();
        String flaws = viewText[2][3].getText().toString();
        String quirks = viewText[2][4].getText().toString();
        String hobbies = viewText[2][5].getText().toString();
        String strengths = viewText[2][6].getText().toString();
        String wants = viewText[2][7].getText().toString();
        String fears = viewText[2][8].getText().toString();
        String secrets = viewText[2][9].getText().toString();
        //Relations
        String family = viewText[3][0].getText().toString();
        String allies = viewText[3][1].getText().toString();
        String enemies = viewText[3][2].getText().toString();
        String groups = viewText[3][3].getText().toString();
        //Goals and BG
        String goals = viewText[4][0].getText().toString();
        String backGround = viewText[4][1].getText().toString();
    }

    ArrayList<View> categoriesLL = new ArrayList<>();
}
