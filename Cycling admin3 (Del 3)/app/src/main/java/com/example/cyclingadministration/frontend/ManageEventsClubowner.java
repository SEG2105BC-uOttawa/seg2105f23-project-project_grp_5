package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter2;
import com.example.cyclingadministration.misc.CustomAdapter3;

import java.util.ArrayList;
import java.util.List;


public class ManageEventsClubowner extends AppCompatActivity {

    private List<String> itemList;
    Button createEvnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_events_clubowner);

        createEvnt = findViewById(R.id.CreateEvent);

        itemList = new ArrayList<>();

        Events allEvents = new Events();

        allEvents.getAllEvents(new Events.EventCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    itemList = events;
                    // Set up the ListView with the updated custom adapter
                    CustomAdapter3 adapter = new CustomAdapter3(ManageEventsClubowner.this, itemList);
                    ListView listView = findViewById(R.id.Create_events);
                    listView.setAdapter(adapter);
                    Log.d(TAG, itemList.toString());
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        createEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEventPage.class);
                startActivity(intent);
            }
        });
    }




}