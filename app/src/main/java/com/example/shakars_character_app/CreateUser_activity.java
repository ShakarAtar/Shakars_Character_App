package com.example.shakars_character_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUser_activity extends AppCompatActivity implements View.OnClickListener {
    Button done;
    TextView title, password, username;
    EditText passwordInput, usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        done = findViewById(R.id.createUserDone);
        done.setOnClickListener(this);

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
        if (done.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),Login_activity.class);
            v.getContext().startActivity(intent);
            //TODO: It should bring username and password with it
        }

    }
}
