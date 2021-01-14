package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class CreateUser_activity extends AppCompatActivity implements View.OnClickListener {
    Button done;
    TextView title, password, email;
    EditText passwordInput, emailInput;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        done = findViewById(R.id.createUserDone);
        done.setOnClickListener(this);

        title = findViewById(R.id.createUserTitle);
        email = findViewById(R.id.createUserEmailText);
        password = findViewById(R.id.createUserPasswordText);

        emailInput = findViewById(R.id.createUserEmailEditText);
        emailInput.setOnClickListener(this);
        passwordInput = findViewById(R.id.createUserPasswordEditText);
        passwordInput.setOnClickListener(this);






    }



    @Override
    public void onClick(View v) {
        if (done.getId() == v.getId()) {
            v.startAnimation(buttonClick);

            Intent intent = new Intent(v.getContext(),Login_activity.class);
            v.getContext().startActivity(intent);
            //TODO: It should bring username and password with it
        }

    }

    private void updateUI(FirebaseUser user) {

    }
}
