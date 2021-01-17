package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllNPCS_activity extends AppCompatActivity implements View.OnClickListener {

    Button newNPCButton, search;
    ImageButton settings;
    TextView newNPCTV, title;
    EditText npcNameET;
    String documentID;
    Toast toast;
    Boolean exists = false;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private static final String TAG = "EditCharacterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npc_list);

        newNPCButton = findViewById(R.id.NPCListCreateNewNPCButton);
        newNPCButton.setOnClickListener(this);

        settings = findViewById(R.id.NPCListOverlayButton);
        //        settings.setOnClickListener(this);
        settings.setVisibility(View.INVISIBLE);

        search = findViewById(R.id.npcListSearchButton);
        search.setOnClickListener(this);

        npcNameET = findViewById(R.id.npcListSearchField);

        newNPCTV = findViewById(R.id.NPCListCreateNewNPCTV);
        newNPCTV.setOnClickListener(this);

        title = findViewById(R.id.NPCListTitle);

    }

    @Override
    public void onClick(View v) {
        if (newNPCButton.getId() == v.getId() || newNPCTV.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),NewNPC_activity.class);
            v.getContext().startActivity(intent);

        } if (search.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            searchCharacter();
            if (exists) {
                Intent intent = new Intent(v.getContext(), ViewNPC_activity.class);
                intent.putExtra("DocumentID", documentID);
                v.getContext().startActivity(intent);
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllNPCS_activity.this, FrontPage_activity.class);
        startActivity(intent);
    }

    private void searchCharacter() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        documentID = npcNameET.getText().toString();
        DocumentReference npcRef;
        npcRef = db.collection("users").document("test")
                .collection("npcs").document(documentID);
        npcRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot npc = task.getResult();
                    assert npc != null;
                    if (npc.exists()) {
                        exists = true;


                    } else {
                        Log.d(TAG, "No such document");
                        toast = Toast.makeText(getApplicationContext(), "Sorry, no NPC with that name",Toast.LENGTH_LONG); toast.show();
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
}
