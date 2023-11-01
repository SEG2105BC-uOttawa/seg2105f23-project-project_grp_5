package com.example.cyclingadministration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onSignUp(View view){
        Intent intent = new Intent(getApplicationContext(), SignUpPage.class);
        startActivity(intent);
    }

    public void onLogin(View view){
        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
        startActivity(intent);
    }

}