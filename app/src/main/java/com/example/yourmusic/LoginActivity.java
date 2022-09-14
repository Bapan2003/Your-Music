package com.example.yourmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
//TextInputEditText loginEmail,loginPassword;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //loginEmail=findViewById(R.id.logInEmailIdEditText);
       // loginPassword=findViewById(R.id.logInPasswordEditText);
       button= findViewById(R.id.buttonSignIn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}