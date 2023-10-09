package com.example.cyclingadministration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cyclingadministration.backend.Registration;
import com.example.cyclingadministration.backend.Login;

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