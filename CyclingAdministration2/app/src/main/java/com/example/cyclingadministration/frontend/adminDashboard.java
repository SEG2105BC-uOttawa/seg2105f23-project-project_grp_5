package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cyclingadministration.R;

public class adminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        ((userState) this.getApplication()).setUserState(true);
    }
}