package com.example.cyclingadministration.frontend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclingadministration.R;

public class clubowner_main extends Activity {

    private Button toCreateEvent, toCompleteProfile, logOut;
    private TextView comp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubowner_main);

        toCreateEvent = findViewById(R.id.createEvents);
        toCompleteProfile = findViewById(R.id.ComProfile);
        logOut = findViewById(R.id.lout);
        comp = findViewById(R.id.textView9);

        if (((UserState) clubowner_main.this.getApplication()).getIsCompleteForUser()){
            comp.setVisibility(View.VISIBLE);
            toCompleteProfile.setVisibility(View.INVISIBLE);
        }else {
            comp.setVisibility(View.INVISIBLE);
            toCompleteProfile.setVisibility(View.VISIBLE);
        }

        toCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageEventsClubowner.class);
                startActivity(intent);
            }
        });

        toCompleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserState) clubowner_main.this.getApplication()).reset();
                Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
