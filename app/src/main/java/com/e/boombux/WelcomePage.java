package com.e.boombux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {
Button signin,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        signin=findViewById(R.id.Wsignbtn);
        login=findViewById(R.id.Wlogbtn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(WelcomePage.this,Sign_up.class);
                startActivity(intent);
                finish();
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomePage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

            }

}
