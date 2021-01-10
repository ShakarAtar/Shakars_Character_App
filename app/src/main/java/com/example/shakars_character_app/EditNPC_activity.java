package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditNPC_activity extends AppCompatActivity implements View.OnClickListener {
    Button save;
    ImageButton settings;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_npc);

        title = findViewById(R.id.editNPCTitle);

        save = findViewById(R.id.editNPCSaveButton);
        save.setOnClickListener(this);

        settings = findViewById(R.id.editNPCOverlayButton);
        settings.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.editNPCLayout);
        content.removeAllViews();

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
            int[] hints = CategoriesAndProperties.dataNPC.hintsNPC[catIndex];
            for (int propIndex = 0; propIndex < props.length; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property,content,false);
                TextView titleTV = root.findViewById(R.id.propertiesTV);
                titleTV.setText(props[propIndex]);

                int hintIndex = propIndex;
                if (hintIndex >= hints.length) {
                    System.err.println("Missing hint for "+catIndex+", "+propIndex);
                    hintIndex = 0;
                }
                EditText hintTV = root.findViewById(R.id.hints);
                hintTV.setHint(hints[hintIndex]);
                hintTV.setTag(hints[hintIndex]);

                cat_title_ll.addView(root);
            }


        }





    }

    @Override
    public void onClick(View v) {
        if (save.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),ViewNPC_activity.class);
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

    ArrayList<View> categoriesLL = new ArrayList<>();
}
