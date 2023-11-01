package com.example.cyclingadministration.backend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Registration {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a new user with a first and last name
    public void addUser(String username, String password, String role) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("role", role);

// Add a new document with a generated ID
        db.collection("User-Info")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
