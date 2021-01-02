package com.example.shakars_character_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile_activity extends AppCompatActivity implements View.OnClickListener {

    Button passwordButton, userNameButton;
    ImageButton settings;
    TextView title, password, username;
    EditText passwordInput, usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        passwordButton = findViewById(R.id.myProfileEditPassword);
        passwordButton.setOnClickListener(this);
        userNameButton = findViewById(R.id.myProfileEditUsername);
        userNameButton.setOnClickListener(this);

        settings = findViewById(R.id.myProfileOverlayButton);
        settings.setOnClickListener(this);

        title = findViewById(R.id.myProfileTitle);
        password = findViewById(R.id.myProfilePasswordText);
        username = findViewById(R.id.myProfileUsernameText);

        passwordInput = findViewById(R.id.myProfilePasswordEditText);
        passwordInput.setOnClickListener(this);
        usernameInput = findViewById(R.id.myProfileUsernameEditText);
        usernameInput.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
