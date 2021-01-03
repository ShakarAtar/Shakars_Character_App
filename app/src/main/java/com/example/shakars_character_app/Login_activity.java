package com.example.shakars_character_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {

    Button login, createUser;
    TextView title, username, password, forgotPassword;
    EditText usernameInput, passwordInput;
    SharedPreferences sharedPref;
    String currentTheme, sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        //Buttons
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        createUser = findViewById(R.id.loginCreateUser);
        createUser.setOnClickListener(this);

        //Text Fields
        title = findViewById(R.id.loginAppTitle);
        username = findViewById(R.id.loginPageUsernameText);
        password = findViewById(R.id.loginPasswordText);
        forgotPassword = findViewById(R.id.loginForgotPasswordClickable);
        forgotPassword.setOnClickListener(this);

        //Edit Text Fields
        usernameInput = findViewById(R.id.loginUsernameEditText);
        passwordInput = findViewById(R.id.loginPasswordEditText);

    }

    @Override
    public void onClick(View v) {
        if (login.getId() == v.getId()) {
            Intent intent = new Intent(v.getContext(),FrontPage_activity.class);
            v.getContext().startActivity(intent);
            //TODO: Only if a correct user has been inputted

        } else if (createUser.getId() == v.getId()){
            Intent intent = new Intent(v.getContext(),CreateUser_activity.class);
            v.getContext().startActivity(intent);
        }

    }
}
