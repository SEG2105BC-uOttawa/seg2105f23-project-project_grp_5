package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter2;
import com.example.cyclingadministration.misc.CustomAdapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowseEvents extends AppCompatActivity {
    private List<String> itemList;
    private Map<String, Map<String, Object>> itemList4;
    SearchView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);

        itemList = new ArrayList<>();
        itemList4 = new HashMap<>();

        Events allEvents = new Events();

        s = findViewById(R.id.searchView);
        s.clearFocus();

        allEvents.getAllEvents1(new Events.EventCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    itemList = events;
                    for (String event : itemList) {
                            // If the event name doesn't match, check "Created by" and "Event Type" fields
                            Events events2 = new Events();
                            events2.getEventDetails(event, new Events.Callback() {
                                @Override
                                public void onResult(boolean success, Map<String, Object> result) {
                                    if (success) {
                                        itemList4.put(event, result);
                                    }
                                }
                            });

                    }
                    setupListView();

                    Log.d(TAG, itemList.toString());
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        s.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the events based on the user input
                List<String> filteredEvents = new ArrayList<>();
                for (String event : itemList) {
                    if (event.toLowerCase().contains(newText.toLowerCase())) {
                        // If the event name contains the input, add it to the filtered list
                        filteredEvents.add(event);
                    } else {

                                    if (itemList4.get(event).get("Created by").toString().contains(newText)) {
                                        filteredEvents.add(event);
                                    } else if (itemList4.get(event).get("Event Type").toString().contains(newText)) {
                                        filteredEvents.add(event);
                                    }

                    }
                }

                // Update the ListView with the filtered events
                CustomAdapter4 adapter = new CustomAdapter4(BrowseEvents.this, filteredEvents);
                ListView listView = findViewById(R.id.eventLst);
                listView.setAdapter(adapter);

                return true;
            }
        });
    }

    private void setupListView() {
        // Set up the ListView with the updated custom adapter
        CustomAdapter4 adapter = new CustomAdapter4(BrowseEvents.this, itemList);
        ListView listView = findViewById(R.id.eventLst);
        listView.setAdapter(adapter);
    }



}