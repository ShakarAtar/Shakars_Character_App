package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        title = findViewById(R.id.newCharacterTitle);

        //Buttons
        save = findViewById(R.id.newCharacterSaveButton);
        save.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.newCharacterLayout);
        content.removeAllViews();

        for (int catIndex = 0; catIndex < CatagoriesAndProperties.data.categoriesPC.length; catIndex++) {
            View catRoot = getLayoutInflater().inflate(R.layout.category_title, content, false);
            LinearLayout cat_title_ll = catRoot.findViewById(R.id.cat_title_ll);
            cat_title_ll.removeAllViews();
            TextView catTitleTv = catRoot.findViewById(R.id.categoriesTV);
            catTitleTv.setText(CatagoriesAndProperties.data.categoriesPC[catIndex]);
            catTitleTv.setOnClickListener(this);
            catTitleTv.setTag(catIndex);
            content.addView(catRoot);
            categorieisLL.add(cat_title_ll);
            cat_title_ll.setVisibility(CatagoriesAndProperties.folded[catIndex] ? View.GONE : View.VISIBLE);

            int[] props = CatagoriesAndProperties.data.propertiesPC[catIndex];
            int[] hints = CatagoriesAndProperties.data.hintsPC[catIndex];
            for (int propIndex = 0; propIndex < props.length; propIndex++) {

                View root = getLayoutInflater().inflate(R.layout.category_property, content, false);
                TextView titleTV = root.findViewById(R.id.propertiesTV);
                titleTV.setText(props[propIndex]);

                int hintIndex = propIndex;
                if (hintIndex>=hints.length) {
                    System.err.println("Mangler hint for "+catIndex+", "+propIndex);
                    hintIndex = 0;
                }
                EditText hintTV = root.findViewById(R.id.hints);
                hintTV.setHint(hints[hintIndex]);
                hintTV.setTag(hintIndex);

                cat_title_ll.addView(root);
            }

        }

    }


        @Override
        public void onClick (View v){
            int catIndex = (int) v.getTag();
            CatagoriesAndProperties.folded[catIndex] = !CatagoriesAndProperties.folded[catIndex];
            //categorieisLL.get(catIndex).setVisibility( foldetIn[catIndex] ? View.GONE : View.VISIBLE );

            View cat = categorieisLL.get(catIndex);
            if (CatagoriesAndProperties.folded[catIndex]) {
                cat.animate().scaleY(0);

            } else {
                cat.setVisibility(View.VISIBLE);
                cat.animate().scaleY(1);

            }

        }


        ArrayList<View> categorieisLL = new ArrayList<>();
        ArrayList<View> hintsTV = new ArrayList<>();



}