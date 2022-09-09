package com.e.boombux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText email;
    Button sendLink,backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        sendLink = findViewById(R.id.reset_pass_btn);
        email = findViewById(R.id.email_reset);
        backLogin = findViewById(R.id.back_btn);

        sendLink.setOnClickListener(new View.OnClickListener() {
            String emails = email.getText().toString();
            @Override
            public void onClick(View v) {
                if (emails.isEmpty())
                {
                    email.setError("Please enter Email");
                    finish();
                }
                else{
                    mAuth.sendPasswordResetEmail(email.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Link has been sent to your email", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });


                }
            }
        });
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });


    }
}