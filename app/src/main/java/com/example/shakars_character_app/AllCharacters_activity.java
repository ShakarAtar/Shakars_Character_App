package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllCharacters_activity extends AppCompatActivity implements View.OnClickListener {

    Button newCharButton, search;
    ImageButton settings;
    TextView newCharTV, title;
    EditText characterNameET;
    Boolean exists = false;
    Toast toast;
    TextView[] characterEntries;
    String documentID;
    List<String> list;
    CollectionReference charList;
    Query query;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private static final String TAG = "EditCharacterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_list);

        newCharButton = findViewById(R.id.characterListCreateNewCharButton);
        newCharButton.setOnClickListener(this);

        settings = findViewById(R.id.characterListOverlayButton);
        //        settings.setOnClickListener(this);
        settings.setVisibility(View.INVISIBLE);

        search = findViewById(R.id.characterListSearchButton);
        search.setOnClickListener(this);

        characterNameET = findViewById(R.id.characterListSearchField);

        newCharTV = findViewById(R.id.characterListCreateNewCharacterTV);
        newCharTV.setOnClickListener(this);

        title = findViewById(R.id.characterListTitle);

        ViewGroup characterList = findViewById(R.id.characterListVG);
        characterList.removeAllViews();

        list = new ArrayList<>();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        query = db.collection("users").document("test").collection("characters").orderBy("Name").limit(10);



//        getAllCharacters();



        for (int charIndex = 0; charIndex < list.size(); charIndex++) {
            System.out.println("reached listfiller");
            View charListRoot = getLayoutInflater().inflate(R.layout.charater_list_entries,characterList,false);
            LinearLayout charListLayout = charListRoot.findViewById(R.id.characterListLayout);
            charListLayout.removeAllViews();
            TextView characterName = characterList.findViewById(R.id.characterListTV);
            characterName.setText(list.get(charIndex));
            characterName.setOnClickListener(this);
            characterName.setTag(charIndex);
            characterList.addView(characterList);


        }




    }

    @Override
    public void onClick(View v) {
        if (newCharButton.getId() == v.getId() || newCharTV.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(), NewCharacter_activity.class);
            v.getContext().startActivity(intent);

        } if (search.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            searchCharacter();
            if (exists) {
                Intent intent = new Intent(v.getContext(), ViewCharacter_activity.class);
                intent.putExtra("DocumentID", documentID);
                v.getContext().startActivity(intent);
            }
        }

    }

    private void searchCharacter() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        documentID = characterNameET.getText().toString();
        DocumentReference characterRef;
        characterRef = db.collection("users").document("test")
                .collection("characters").document(documentID);
        characterRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot character = task.getResult();
                    assert character != null;
                    if (character.exists()) {
                             exists = true;


                    } else {
                        Log.d(TAG, "No such document");
                        toast = Toast.makeText(getApplicationContext(), "Sorry, no character with that name",Toast.LENGTH_LONG); toast.show();
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllCharacters_activity.this, FrontPage_activity.class);
        startActivity(intent);
    }

    private void getAllCharacters() {

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getId());
                                Log.d(TAG, list.toString());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        System.out.println("Left the database getter");

    }


}