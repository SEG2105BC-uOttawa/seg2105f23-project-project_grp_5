package com.example.cyclingadministration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomePageTwo extends AppCompatActivity {
    TextView username, role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_two);

        Intent intent = getIntent();
        String usernameText = intent.getStringExtra("KEY_USERNAME");
        String roleText = intent.getStringExtra("KEY_ROLE");

        username = findViewById(R.id.welcome_message);
        username.setText("Welcome "+usernameText);
        role = findViewById(R.id.role);
        role.setText("You are a " + roleText);
    }

    public void returnHome(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}