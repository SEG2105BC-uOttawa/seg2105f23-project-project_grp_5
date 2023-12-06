package com.example.cyclingadministration.misc;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.frontend.EventDetails;
import com.example.cyclingadministration.frontend.ViewEvent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter5 extends ArrayAdapter<String> {

    public CustomAdapter5(Context context, List<String> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_adapter5, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.evname);
        Button deleteButton = convertView.findViewById(R.id.deleteButton2);



        textView.setText(item);

        // Set a click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewEvent.class);
                intent.putExtra("evntname", textView.getText().toString());
                getContext().startActivity(intent);
            }
        });







        return convertView;
    }
}
