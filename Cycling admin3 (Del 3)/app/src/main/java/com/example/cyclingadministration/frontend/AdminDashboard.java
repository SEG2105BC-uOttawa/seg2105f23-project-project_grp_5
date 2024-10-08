package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.cyclingadministration.R;

public class AdminDashboard extends AppCompatActivity {

    FrameLayout userList, eventList, createType;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        ((UserState) this.getApplication()).setUserState(true);

        userList = findViewById(R.id.container_one);
        eventList = findViewById(R.id.container_two);
        createType = findViewById(R.id.container_three);
        logout = findViewById(R.id.log_out_button);

        userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserList.class);
                startActivity(intent);
            }
        });

        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventList.class);
                startActivity(intent);
            }
        });

        createType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEventType.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                ((UserState) AdminDashboard.this.getApplication()).setUserState(true);
                startActivity(intent);
            }
        });
    }



}