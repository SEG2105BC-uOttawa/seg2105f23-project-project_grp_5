package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Users;
import com.example.cyclingadministration.misc.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        itemList = new ArrayList<>();

        Users allUsers = new Users();

        allUsers.getAllUsers(new Users.UsersCallback() {
            @Override
            public void onResult(boolean success, List<String> users) {
                if (success) {
                    itemList = users;
                    // Set up the ListView with the updated custom adapter
                    CustomAdapter adapter = new CustomAdapter(UserList.this, itemList);
                    ListView listView = findViewById(R.id.user_view_list);
                    listView.setAdapter(adapter);
                    Log.d(TAG, itemList.toString());
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching users.");
                }
            }
        });




    }
}
