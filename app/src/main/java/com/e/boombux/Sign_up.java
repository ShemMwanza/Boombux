package com.e.boombux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Sign_up extends AppCompatActivity {

    Button sign;
    EditText email, pass, username;
    FirebaseAuth mAuth;
    TextView Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        sign = findViewById(R.id.signbutton);
        email = findViewById(R.id.signemail);
        pass = findViewById(R.id.signpass);
        username = findViewById(R.id.signusername);
        Text= findViewById(R.id.text);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String password = pass.getText().toString();
                if (TextUtils.isEmpty(emails))
                {
                    email.setError("Please enter Email");

                }
                if (TextUtils.isEmpty(password))
                {
                    pass.setError("Please enter password");

                }
                if (password.length()<6)
                {
                    pass.setError("Password needs to be at least 6 characters");

                }
                mAuth.createUserWithEmailAndPassword(emails, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                }
                                else
                                    {
                                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });



            }
        });
        }

    }

