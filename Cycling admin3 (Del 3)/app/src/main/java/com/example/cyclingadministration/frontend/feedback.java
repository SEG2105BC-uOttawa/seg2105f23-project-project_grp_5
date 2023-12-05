package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;

public class feedback extends AppCompatActivity {

    private Button toRate, submitRate;
    private String eventName;

    private EditText rating, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Intent intent = getIntent();
        eventName = intent.getStringExtra("eventname");

        submitRate = findViewById(R.id.button2);
        rating = findViewById(R.id.editTextText7);
        comment = findViewById(R.id.editTextText8);

        submitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){
                    handleRegistration();
                    Intent i = new Intent(feedback.this, ParticipantMain.class);

                    startActivity(i);
                }

            }
        });
    }


    private boolean validateFields() {
        // Get the text from EditText fields
        String name = comment.getText().toString().trim();
        String ageStr = rating.getText().toString().trim();



        // Perform validation checks
        if (TextUtils.isEmpty(name)) {
            showToast("Please enter a rating");
            return false;
        }

        if (TextUtils.isEmpty(ageStr)) {
            showToast("Please enter an comment");
            return false;
        }



        try {
            // Validate age as a number
            int age = Integer.parseInt(ageStr);
            if (age < 1 || age > 5) {
                showToast("Please enter a valid rating between 1 and 5");
                return false;
            }
        } catch (NumberFormatException e) {
            showToast("Please enter a valid age");
            return false;
        }


        // Validation passed
        return true;
    }


    private void handleRegistration() {
        Events evnt = new Events();
        evnt.addUserfeedback(rating.getText().toString(), eventName, "User Ratings",  ((UserState) feedback.this.getApplication()).getUsernameForUser());
        evnt.addUserfeedback(comment.getText().toString(), eventName, "User Comments",  ((UserState) feedback.this.getApplication()).getUsernameForUser());
        showToast("Feedback added! The club owner will be able to see your feedback!");

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}