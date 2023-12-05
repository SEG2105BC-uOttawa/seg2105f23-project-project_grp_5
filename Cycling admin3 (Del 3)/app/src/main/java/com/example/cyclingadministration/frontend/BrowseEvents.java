package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter2;
import com.example.cyclingadministration.misc.CustomAdapter4;

import java.util.ArrayList;
import java.util.List;

public class BrowseEvents extends AppCompatActivity {
    private List<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);

        itemList = new ArrayList<>();

        Events allEvents = new Events();

        allEvents.getAllEvents(new Events.EventCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    itemList = events;
                    // Set up the ListView with the updated custom adapter
                    CustomAdapter4 adapter = new CustomAdapter4(BrowseEvents.this, itemList);
                    ListView listView = findViewById(R.id.eventLst);
                    listView.setAdapter(adapter);
                    Log.d(TAG, itemList.toString());
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });
    }

}