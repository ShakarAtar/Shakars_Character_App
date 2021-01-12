package com.example.shakars_character_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {

    Button login, createUser;
    TextView title, username, password, forgotPassword;
    EditText usernameInput, passwordInput;
    SharedPreferences sharedPref;
    String currentTheme, sharedPreference;
    private FirebaseAuth mAuth;

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

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Toast.makeText(this, "Username and Password is correct.",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,"Username or Password was incorrect.", Toast.LENGTH_LONG).show();

        }
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
