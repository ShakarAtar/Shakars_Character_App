package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewCharacter_activity extends AppCompatActivity implements View.OnClickListener {
    Button edit;
    ImageButton settings;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_character);

        title = findViewById(R.id.viewCharacterTitle);

        edit = findViewById(R.id.viewCharacterEditButton);
        edit.setOnClickListener(this);

        settings.findViewById(R.id.viewOverlayButton);
        settings.setOnClickListener(this);

        ViewGroup content = findViewById(R.id.viewCharacterLayout);
        content.removeAllViews();

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
                hintTV.setHint(hints[hintIndex]);
                hintTV.setTag(hintIndex);

                cat_title_ll.addView(root);

            }


        }

    }

    @Override
    public void onClick(View v) {
//        int catIndex = (int) v.getTag();
//        CategoriesAndProperties.foldedPC[catIndex] = !CategoriesAndProperties.foldedPC[catIndex];
//
//        View cat = categoriesLL.get(catIndex);
//        if (CategoriesAndProperties.foldedPC[catIndex]) {
//            cat.animate().scaleY(0);
//
//        } else {
//            cat.setVisibility(View.VISIBLE);
//            cat.animate().scaleY(1);
//
//        }

        if (edit.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),EditCharacter_activity.class);
            v.getContext().startActivity(intent);
        }

    }

    ArrayList<View> categoriesLL = new ArrayList<>();
}
