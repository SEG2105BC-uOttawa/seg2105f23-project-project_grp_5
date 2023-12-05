package com.example.cyclingadministration.frontend;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Users;

public class CompleteProfile extends Activity {

    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private EditText editTextInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        editTextName = findViewById(R.id.Name);
        editTextPhoneNumber = findViewById(R.id.age);
        editTextInstagram = findViewById(R.id.skillLevel);

        Button createEventsButton = findViewById(R.id.createEvents);

        createEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsValid()) {
                    String name = editTextName.getText().toString();
                    String phoneNumber = editTextPhoneNumber.getText().toString();
                    String instagramLink = editTextInstagram.getText().toString();
                    completeProfile(name, phoneNumber, instagramLink);
                    Intent i = new Intent(getApplicationContext(), clubowner_main.class);
                    startActivity(i);
                } else {
                    Toast.makeText(CompleteProfile.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean areFieldsValid() {
        // Check if required fields are filled
        return !editTextPhoneNumber.getText().toString().isEmpty() &&
                !editTextInstagram.getText().toString().isEmpty();
    }

    private void completeProfile(String name, String phoneNumber, String instagramLink) {


        Users user = new Users();

        String username = ((UserState) CompleteProfile.this.getApplication()).getUsernameForUser();
        Log.d(TAG, "User" + username);
        String password = ((UserState) CompleteProfile.this.getApplication()).getPasswordForUser();
        user.updateUser(username, password, name, phoneNumber, instagramLink);
        ((UserState) CompleteProfile.this.getApplication()).setIsCompleteForUser(true);


        Toast.makeText(this, "Profile Completed Successfully", Toast.LENGTH_SHORT).show();
    }
}
