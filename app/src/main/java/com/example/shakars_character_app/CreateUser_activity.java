package com.example.shakars_character_app;

import android.content.Intent;
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

public class CreateUser_activity extends AppCompatActivity implements View.OnClickListener {
    Button done;
    TextView title, passwordTV, emailTV;
    EditText passwordInput, emailInput;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private FirebaseAuth mAuth;
    private static final String TAG = "CreateUserActivity";
    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        done = findViewById(R.id.createUserDone);
        done.setOnClickListener(this);

        title = findViewById(R.id.createUserTitle);
        emailTV = findViewById(R.id.createUserEmailText);
        passwordTV = findViewById(R.id.createUserPasswordText);

        emailInput = findViewById(R.id.createUserEmailEditText);
        emailInput.setOnClickListener(this);
        passwordInput = findViewById(R.id.createUserPasswordEditText);
        passwordInput.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();






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

    private void createUser() {

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
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {

    }

}
