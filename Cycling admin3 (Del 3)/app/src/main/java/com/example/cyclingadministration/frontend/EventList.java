package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter;
import com.example.cyclingadministration.misc.CustomAdapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventList extends AppCompatActivity {

    private List<String> itemList;

    public void toEdit(String typeName){
        Intent i = new Intent(EventList.this, editEventTypes.class);
        i.putExtra("KEY_TYPENAME", typeName);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        itemList = new ArrayList<>();

        Events allEvents = new Events();

        allEvents.getAllEventTypes(new Events.EventTypesCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    itemList = events;
                    // Set up the ListView with the updated custom adapter
                    CustomAdapter2 adapter = new CustomAdapter2(EventList.this, itemList);
                    ListView listView = findViewById(R.id.event_view_list);
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
