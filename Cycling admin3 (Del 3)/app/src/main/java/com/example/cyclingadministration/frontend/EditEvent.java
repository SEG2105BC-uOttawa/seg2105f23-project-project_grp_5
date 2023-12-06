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
import android.widget.Spinner;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditEvent extends AppCompatActivity {
    private List<String> items;

    private Map<String, Object> items2;

    private String selectedValue;

    String eventName;

    Spinner spinner;

    EditText evName, date, maxpple,desc;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        HashMap<String, Object> receivedMap = (HashMap<String, Object>) getIntent().getSerializableExtra("Val");

        Events allEvents = new Events();
        items = new ArrayList<>(); // Initialize items list



        submit = findViewById(R.id.EditEvent);
        allEvents.getAllEventTypes(new Events.EventTypesCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    items = events;
                    Log.d(TAG, items.toString());

                    // Convert List to String array
                    String[] types = new String[items.size()];
                    types = items.toArray(types);

                    spinner = findViewById(R.id.chooseEventType);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EditEvent.this, android.R.layout.simple_spinner_item, types);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        spinner = findViewById(R.id.chooseEventType);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected, if needed.
            }
        });
        evName = findViewById(R.id.editTextText2);
        evName.setText(receivedMap.get("Event Name").toString());
        date= findViewById(R.id.editTextText3);
        date.setText(receivedMap.get("Date").toString());
        maxpple= findViewById(R.id.editTextText4);
        maxpple.setText(receivedMap.get("Max People").toString());
        desc= findViewById(R.id.editTextText6);
        desc.setText(receivedMap.get("Description").toString());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditTexts
                String name = evName.getText().toString().trim();
                String date2 = date.getText().toString().trim();
                String max = maxpple.getText().toString().trim();
                String des = desc.getText().toString().trim();

                // Validate name
                if (name.isEmpty()) {
                    evName.setError("Event Name is required");
                    return;
                }

                // Validate date format
                if (!isValidDateFormat(date2)) {
                    date.setError("Invalid date format. Use dd/mm/yyyy");
                    return;
                }

                // Validate max people as a number
                if (max.isEmpty() || !max.matches("\\d+")) {
                    maxpple.setError("Max People must be a number");
                    return;
                }

                // Validate description length
                if (des.length() < 10 || des.length() > 200) {
                    desc.setError("Description must be between 10 and 200 characters");
                    return;
                }

                // Continue with the rest of your logic if all validations pass

                List<String> participantsList = new ArrayList<>();
                Map<String, Object> val = new HashMap<>();
                val.put("Event Name", name);
                val.put("Event Type", selectedValue);
                val.put("Date", date2);
                val.put("Description", des);
                val.put("Max People", max);

                Events ev = new Events();
                ev.updateEvent(receivedMap.get("Event Name").toString(), val);
                Intent intent = new Intent(getApplicationContext(), clubowner_main.class);
                startActivity(intent);
            }
        });}

// Function to validate date format
        private boolean isValidDateFormat(String date) {
            String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";
            return date.matches(regex);
        }

    }