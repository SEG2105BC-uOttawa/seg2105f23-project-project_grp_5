package com.example.cyclingadministration.misc;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, List<String> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_setup, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        Button button = convertView.findViewById(R.id.deleteButton);

        textView.setText(item);

        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
                Users users = new Users();
                users.deleteUser(textView.getText().toString());
            }
        });

        return convertView;
    }
}
