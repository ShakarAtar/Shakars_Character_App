package com.example.shakars_character_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {

    Button login, createUser;
    TextView titleTV, usernameTV, passwordTV, forgotPasswordTV;
    EditText usernameInput, passwordInput;
    SharedPreferences sharedPref;
    String currentTheme, sharedPreference;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private FirebaseAuth mAuth;
    String email, password;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        //Buttons
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        createUser = findViewById(R.id.loginCreateUser);
        createUser.setOnClickListener(this);
        createUser.setVisibility(View.INVISIBLE);

        //Text Fields
        titleTV = findViewById(R.id.loginAppTitle);
        usernameTV = findViewById(R.id.loginPageUsernameText);
        passwordTV = findViewById(R.id.loginPasswordText);
        forgotPasswordTV = findViewById(R.id.loginForgotPasswordClickable);
        forgotPasswordTV.setOnClickListener(this);

        //Edit Text Fields
        usernameInput = findViewById(R.id.loginUsernameEditText);
        passwordInput = findViewById(R.id.loginPasswordEditText);



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    public void onClick(View v) {
        if (login.getId() == v.getId()) {
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),FrontPage_activity.class);
            v.getContext().startActivity(intent);


        } else if (createUser.getId() == v.getId()){
            v.startAnimation(buttonClick);
            Intent intent = new Intent(v.getContext(),CreateUser_activity.class);
            v.getContext().startActivity(intent);
        }

    }

    private void updateUI(FirebaseUser user) {

    }

    private void loginUser() {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }
}
