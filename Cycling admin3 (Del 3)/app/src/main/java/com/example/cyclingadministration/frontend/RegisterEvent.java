package com.example.cyclingadministration.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;

public class RegisterEvent extends AppCompatActivity {

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText skillLevelEditText;
    private Button registerButton;

    private  String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        Intent intent = getIntent();
        eventName = intent.getStringExtra("evntname");
        // Initialize your EditText and Button views
        nameEditText = findViewById(R.id.Name);
        ageEditText = findViewById(R.id.age);
        skillLevelEditText = findViewById(R.id.skillLevel);
        registerButton = findViewById(R.id.createEvents);

        // Set onClickListener for the register button
        registerButton.setOnClickListener(view -> {
            // Validate fields before proceeding
            if (validateFields()) {
                // Fields are valid, proceed with registration logic
                // For example, you can call a method to handle registration
                handleRegistration();
            }
        });
    }

    private boolean validateFields() {
        // Get the text from EditText fields
        String name = nameEditText.getText().toString().trim();
        String ageStr = ageEditText.getText().toString().trim();
        String skillLevelStr = skillLevelEditText.getText().toString().trim();

        // Perform validation checks
        if (TextUtils.isEmpty(name)) {
            showToast("Please enter a name");
            return false;
        }

        if (TextUtils.isEmpty(ageStr)) {
            showToast("Please enter an age");
            return false;
        }

        if (TextUtils.isEmpty(skillLevelStr)) {
            showToast("Please enter a skill level");
            return false;
        }

        try {
            // Validate age as a number
            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                showToast("Please enter a valid age");
                return false;
            }
        } catch (NumberFormatException e) {
            showToast("Please enter a valid age");
            return false;
        }

        try {
            // Validate skill level as a number between 1 and 10
            int skillLevel = Integer.parseInt(skillLevelStr);
            if (skillLevel < 1 || skillLevel > 10) {
                showToast("Skill level must be between 1 and 10");
                return false;
            }
        } catch (NumberFormatException e) {
            showToast("Please enter a valid skill level");
            return false;
        }

        // Validation passed
        return true;
    }


    private void handleRegistration() {
        Events evnt = new Events();
        evnt.valueExists(eventName,"Participants", ((UserState) RegisterEvent.this.getApplication()).getUsernameForUser(), new Events.ValueExistsCallback() {
            @Override
            public void onValueExists(boolean exists) {
                if (exists) {
                    showToast("You already registered for this event!");
                } else {
                    evnt.updateArray(eventName, ((UserState) RegisterEvent.this.getApplication()).getUsernameForUser());
                    showToast("Joined Event!");
                }
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
