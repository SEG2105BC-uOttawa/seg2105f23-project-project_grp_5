package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference eventTypesRef = db.collection("Event-Types");

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