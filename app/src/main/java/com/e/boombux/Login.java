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

public class Login extends AppCompatActivity {
    Button log;
    EditText email,pass;
    FirebaseAuth mAuth;
    TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log= findViewById(R.id.logbutton);
        email= findViewById(R.id.logemail);
        pass= findViewById(R.id.logpass);
        mAuth = FirebaseAuth.getInstance();
        forgot= findViewById(R.id.text);



        log.setOnClickListener(new View.OnClickListener() {
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
                mAuth.signInWithEmailAndPassword(emails,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                        else
                            {
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }


                    }
                });
            }
        });
        forgot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
                finish();
            }

        });
        }
}
