package com.example.cyclingadministration.backend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query;


import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private static final String TAG = "Users";

    private String role, isComplete;

    public String getRole(){
        return role;
    }

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

    public void addUser(String username, String password, String role) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("role", role);
        user.put("isProfileComplete", false);

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

    public interface LoginCallback {
        void onResult(boolean success, String role, String isComplete);
    }



    public void getUser(String username, String password, Users.LoginCallback callback){

        Log.d(TAG, "getUser: "+ username + password);
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
                                callback.onResult(false, null, null);
                            } else {
                                // Matching documents found, get the role from the first document
                                role = task.getResult().getDocuments().get(0).getData().get("role").toString();
                                isComplete = task.getResult().getDocuments().get(0).getData().get("isProfileComplete").toString();
                                Log.d(TAG, "onComplete: worked");
                                callback.onResult(true, role,isComplete );
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null, null);
                        }
                    }
                });
    }

    public void updateUser(String username, String password, String name, String phone, String social) {
        // Assuming you have a reference to your Firestore database
        Log.d(TAG, username);

        // Query for the document based on username and password
        CollectionReference userCollection = db.collection("User-Info");
        Query query = userCollection.whereEqualTo("username", username).whereEqualTo("password", password);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "hello2");
                Log.d(TAG, String.valueOf(task.isSuccessful()));

                if (task.isSuccessful()) {
                    Log.d(TAG, "hello3");
                    QuerySnapshot snapshot = task.getResult();

                    if (snapshot != null && !snapshot.isEmpty()) {
                        // Retrieve the document reference
                        DocumentReference documentRef = userCollection.document(snapshot.getDocuments().get(0).getId());

                        // Create a map with the new fields
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("Name", name);
                        updates.put("phone", phone);
                        updates.put("social", social);
                        updates.put("isProfileComplete", "true");
                        Log.d(TAG, updates.toString());

                        // Update the document with the new fields
                        documentRef.update(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "Document updated successfully!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });
                    } else {
                        Log.d(TAG, "No matching documents found.");
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }


}
