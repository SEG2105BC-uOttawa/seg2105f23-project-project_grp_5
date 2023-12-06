package com.example.cyclingadministration.misc;

import com.example.cyclingadministration.R;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.frontend.AdminDashboard;
import com.example.cyclingadministration.frontend.EventList;
import com.example.cyclingadministration.frontend.ViewEvent;

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

public class CustomAdapter2 extends ArrayAdapter<String> {

    public CustomAdapter2(Context context, List<String> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_adapter2, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.evname);
        Button deleteButton = convertView.findViewById(R.id.deleteButton2);
        Button editButton = convertView.findViewById(R.id.editButton);


        textView.setText(item);

        // Set a click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Events events = new Events();
                events.deleteEventType(textView.getText().toString());
                Intent intent = new Intent(getContext(), AdminDashboard.class);
                getContext().startActivity(intent);
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                if (context instanceof EventList) {
                    // Cast the context to EventList and call the toEdit method
                    ((EventList) context).toEdit(textView.getText().toString());
                } else {
                    // Handle the case where the context is not an instance of EventList
                    Log.e(TAG, "Invalid context type");
                }
            }
        });




        return convertView;
    }
}
