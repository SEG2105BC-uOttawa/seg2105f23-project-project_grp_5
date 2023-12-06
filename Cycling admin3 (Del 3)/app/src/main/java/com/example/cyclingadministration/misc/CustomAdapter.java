package com.example.cyclingadministration.misc;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Users;
import com.example.cyclingadministration.frontend.AdminDashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

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

        TextView textView = convertView.findViewById(R.id.evname);
        Button button = convertView.findViewById(R.id.deleteButton2);

        textView.setText(item);

        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
                Users users = new Users();
                users.deleteUser(textView.getText().toString());
                Intent intent = new Intent(getContext(), AdminDashboard.class);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
