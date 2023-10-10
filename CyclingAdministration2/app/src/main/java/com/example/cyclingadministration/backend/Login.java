package com.example.cyclingadministration.backend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Login {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String role;

    public String getRole(){
        return role;
    }

    public interface LoginCallback {
        void onResult(boolean success, String role);
    }

    boolean isLogged;

    public void getUser(String username, String password, LoginCallback callback){


        db.collection("User-Info")
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                // No matching documents found
                                Log.d(TAG, "No matching documents found.");
                                callback.onResult(false, null);
                            } else {
                                // Matching documents found, get the role from the first document
                                role = task.getResult().getDocuments().get(0).getData().get("role").toString();
                                Log.d(TAG, "onComplete: worked");
                                callback.onResult(true, role);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null);
                        }
                    }
                });
    }
}
