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

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Events {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference eventTypesRef = db.collection("Event-Types");
    private CollectionReference eventRef = db.collection("Events");

    public void addEventType(Map<String, String> values){
        Log.d(TAG, values.toString());
        eventTypesRef
                .add(values)
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

    public void deleteEventType(String eventName) {
        eventTypesRef.whereEqualTo("Event Type Name", eventName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                document.getReference().delete();
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void updateEventType(String eventName, Map<String, Object> updates) {
        eventTypesRef.whereEqualTo("eventName", eventName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                document.getReference().update(updates);
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void getAllEventTypes(EventTypesCallback callback) {
        eventTypesRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> eventTypes = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                eventTypes.add(document.getData().get("Event Type Name").toString());
                            }
                            callback.onResult(true, eventTypes);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null);
                        }
                    }
                });
    }



    public interface EventTypesCallback {
        void onResult(boolean success, List<String> eventTypes);
    }


    public void getAllEvents(EventCallback callback) {
        eventRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> events = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                events.add(document.getData().get("Event Name").toString());
                            }
                            callback.onResult(true, events);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null);
                        }
                    }
                });
    }



    public interface EventCallback {
        void onResult(boolean success, List<String> events);
    }

    public void addEvent(Map<String, String> values){
        Log.d(TAG, values.toString());
        eventRef
                .add(values)
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

    public void getAllEventType(EventTypeCallback callback, String name) {
        eventTypesRef.whereEqualTo("Event Type Name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Map<String, Object>> eventTypesList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                eventTypesList.add(document.getData());
                            }
                            callback.onResult(true, eventTypesList);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onResult(false, null);
                        }
                    }
                });
    }

    public interface EventTypeCallback {
        void onResult(boolean success, List<Map<String, Object>> eventTypes);
    }

}
