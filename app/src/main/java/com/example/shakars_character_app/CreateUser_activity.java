package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUser_activity extends AppCompatActivity implements View.OnClickListener {
    Button done;
    ImageButton settings;
    TextView title, password, username;
    EditText passwordInput, usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        done = findViewById(R.id.createUserDone);
        done.setOnClickListener(this);

        settings = findViewById(R.id.createUserOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.createUserTitle);
        password = findViewById(R.id.createUserPasswordText);
        username = findViewById(R.id.createUserUsernameText);

        passwordInput = findViewById(R.id.createUserPasswordEditText);
        passwordInput.setOnClickListener(this);
        usernameInput = findViewById(R.id.createUserUsernameEditText);
        usernameInput.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

    }
}
