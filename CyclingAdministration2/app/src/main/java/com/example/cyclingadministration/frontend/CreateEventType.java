package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateEventType extends AppCompatActivity {


    private LinearLayout formContainer;

    // Default fields
    private String typeName, actType, diff, maxPeople;


    EditText typeNamet, maxPpeoplet;
    Spinner difficulty, activityType;
    Button submitButton;
    private List<EditText> editTextList = new ArrayList<>();
    private Map<String, String> values = new HashMap<>();

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_type);
        formContainer = findViewById(R.id.formContainer);
        Button addFieldButton = findViewById(R.id.addFieldButton);
        submitButton = findViewById(R.id.createEventButt);

        activityType = findViewById(R.id.activityTypeSpinner);
        String[] items = {"Group", "Solo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityType.setAdapter(adapter);


        activityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected, if needed.
            }
        });

        difficulty = findViewById(R.id.intensitySpinner);
        String[] items2 = {"Easy", "Medium", "Hard"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);


        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected, if needed.
            }
        });

        addFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFormField();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeNamet = findViewById(R.id.eventNameEditText);
                typeName = typeNamet.getText().toString();

                maxPpeoplet = findViewById(R.id.eventNameEditText2);
                maxPeople = maxPpeoplet.getText().toString();

                diff = difficulty.getSelectedItem().toString();
                actType = activityType.getSelectedItem().toString();

                if (typeName.isEmpty() || maxPeople.isEmpty()) {
                    // Show an error message or handle the validation failure
                    typeNamet.setError("Field cannot be empty");
                    maxPpeoplet.setError("Field cannot be empty");
                    return;
                }

                values.put("Event Type Name", typeName);
                values.put("Activity Type", actType);
                values.put("Difficulty", maxPeople);
                values.put("Max People", maxPeople);
                collectUserInput();
                Log.d(TAG, values.toString());
                Events event = new Events();
                event.addEventType(values);
            }
        });
    }

    private void addFormField() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View formFieldView = inflater.inflate(R.layout.activity_field_layout, null);


        EditText editText = formFieldView.findViewById(R.id.fieldInput);
        editTextList.add(editText);

        Button removeButton = formFieldView.findViewById(R.id.removeFieldButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the form field when the "Remove" button is clicked
                formContainer.removeView(formFieldView);
                editTextList.remove(editText);
            }
        });

        formContainer.addView(formFieldView);
    }

    private void collectUserInput() {
        int count = 1;
        for (EditText editText : editTextList) {
            if (editText.getText().toString().isEmpty()){
                editText.setError("Cannot be empty!");
            }
            values.put("Field" + count, editText.getText().toString());
            count++;
            // Do something with editTextValue
        }
    }
}
