package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDetails extends AppCompatActivity {

    private  String eventName;

    private TextView view29, view20, view22, view25, view27, view31;
    private Map<String, Object> itemList;

    private Button toRate, submitRate;

    private EditText rating, comment;

    private TextView ratingt, commentt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        eventName = intent.getStringExtra("evntname");
        view29 = findViewById(R.id.textView29);
        view20 = findViewById(R.id.textView20);
        view22 = findViewById(R.id.textView22);
        view25 = findViewById(R.id.textView25);
        view27 = findViewById(R.id.textView27);
        view31 = findViewById(R.id.textView31);
        toRate = findViewById(R.id.toRate);


        itemList = new HashMap<>();

        Events allEvents = new Events();

        allEvents.getEventDetails(eventName, new Events.Callback() {
            @Override
            public void onResult(boolean success, Map<String, Object> result) {
                if (success) {
                    itemList = result;
                    Log.d(TAG, itemList.toString() + "Chcimen");
                    view29.setText(itemList.get("Event Name").toString());
                    view20.setText(itemList.get("Description").toString());
                    view22.setText(itemList.get("Event Type").toString());
                    view25.setText(itemList.get("Date").toString());
                    view27.setText(itemList.get("Max People").toString());
                    view31.setText(itemList.get("Created by").toString());
                    Log.d(TAG, itemList.toString());
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        toRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EventDetails.this, feedback.class);
                i.putExtra("eventname", itemList.get("Event Name").toString());
                startActivity(i);
            }
        });
    }

}