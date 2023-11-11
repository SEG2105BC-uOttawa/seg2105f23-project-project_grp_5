package com.example.cyclingadministration.backend;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private static final String TAG = "Users";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface UsersCallback {
        void onResult(boolean success, List<String> users);
    }

    public void deleteUser(String username){
        db.collection("User-Info")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Get the document ID
                                String documentId = document.getId();

                                // Delete the document using the ID
                                db.collection("User-Info").document(documentId)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Document successfully deleted
                                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle errors
                                                Log.w(TAG, "Error deleting document", e);
                                            }
                                        });
                            }
                        } else {
                            // Handle errors
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void getAllUsers(UsersCallback callback) {
        Log.d(TAG, "getAllUsers");
        List<String> users = new ArrayList<>();

        db.collection("User-Info")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                users.add(document.getData().get("username").toString());
                            }
                            Log.d(TAG, "onComplete: worked");
                            callback.onResult(true, users);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null);
                        }
                    }
                });
    }
}
