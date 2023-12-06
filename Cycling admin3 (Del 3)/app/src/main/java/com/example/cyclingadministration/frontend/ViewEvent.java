package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter2;
import com.example.cyclingadministration.misc.CustomAdapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewEvent extends AppCompatActivity {
    private String eventName;
    private Map<String, Object> items;
    private Map<String, Object> items3;
    private Map<String, Object> items4;

    TextView t1,t2,t3,t4, t5;
    private List<String> users;
    Button editEv, deltEv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Intent intent = getIntent();
        eventName = intent.getStringExtra("evntname");

        Events allEvents = new Events();
        items = new HashMap<>(); // Initialize items list

        t1 = findViewById(R.id.textView36);
        t2 = findViewById(R.id.textView40);
        t3 = findViewById(R.id.textView41);
        t4 = findViewById(R.id.textView42);
        t5 = findViewById(R.id.textView44);
        editEv = findViewById(R.id.CreateEvent);
        deltEv = findViewById(R.id.EditEvent);




        allEvents.getEventDetails(eventName, new Events.Callback() {
            @Override
            public void onResult(boolean success, Map<String, Object> result) {
                if (success) {
                    items = result;
                    Log.d(TAG, items.toString());
                    t1.setText(items.get("Date").toString());
                    t2.setText(items.get("Max People").toString());
                    t3.setText(items.get("Description").toString());
                    t4.setText(items.get("Date").toString());
                    t5.setText(items.get("Event Type").toString());
                    items3 = (Map<String, Object>) items.get("User Ratings");
                    //items4 = (Map<String, Object>) items.get("User Comments");
                    users = (ArrayList<String>) items.get("Participants");
                    CustomAdapter5 adapter = new CustomAdapter5(ViewEvent.this, users);
                    ListView listView = findViewById(R.id.allusers);
                    listView.setAdapter(adapter);


                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        editEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditEvent.class);
                HashMap<String, Object> hashMap = new HashMap<>(items);
                intent.putExtra("Val", hashMap);
                startActivity(intent);
            }
        });

        deltEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events e = new Events();
                e.deleteEvent(items.get("Event Name").toString());
                Intent intent = new Intent(getApplicationContext(), clubowner_main.class);
                startActivity(intent);
            }
        });


    }
}