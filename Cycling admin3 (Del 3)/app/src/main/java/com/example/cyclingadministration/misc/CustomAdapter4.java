package com.example.cyclingadministration.misc;

import com.example.cyclingadministration.R;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.frontend.EventDetails;
import com.example.cyclingadministration.frontend.EventList;
import com.example.cyclingadministration.frontend.LoginPage;
import com.example.cyclingadministration.frontend.RegisterEvent;
import com.example.cyclingadministration.frontend.WelcomePageTwo;
import com.example.cyclingadministration.frontend.clubowner_main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter4 extends ArrayAdapter<String> {

    public CustomAdapter4(Context context, List<String> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_adapter4, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.evname);
        Button deleteButton = convertView.findViewById(R.id.deleteButton2);
        Button editButton = convertView.findViewById(R.id.editButton);


        textView.setText(item);

        // Set a click listener for the delete button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EventDetails.class);
                intent.putExtra("evntname", textView.getText().toString());
                getContext().startActivity(intent);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterEvent.class);
                intent.putExtra("evntname", textView.getText().toString());
                getContext().startActivity(intent);
            }
        });





        return convertView;
    }
}
