package com.example.cyclingadministration.backend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
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


    public void getAllEvents1( EventCallback callback1) {
        Log.d(TAG, "helo");
        eventRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> events = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                events.add(document.getData().get("Event Name").toString());}

                            callback1.onResult(true, events);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback1.onResult(false, null);
                        }
                    }
                });
    }



    public interface EventCallback1 {
        void onResult(boolean success, List<String> events);
    }

    public void getAllEvents(String creator, EventCallback callback) {
        Log.d(TAG, "helo");
        eventRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> events = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("Created by").equals(creator)){
                                    events.add(document.getData().get("Event Name").toString());}
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

    public void addEvent(Map<String, Object> values) {
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

    public void getEventDetails(String user, final Callback callback) {
        eventRef.whereEqualTo("Event Name", user).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> allFields = new HashMap<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Get all fields as a Map
                        allFields.putAll(document.getData());
                    }

                    // Invoke the callback with the result
                    callback.onResult(true, allFields);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    // Define a callback interface
    public interface Callback {
        void onResult(boolean success, Map<String, Object> result);
    }

    public void updateArray(String fieldValue, String newItem) {
        // Assuming you have a reference to your Firestore database
        CollectionReference collectionReference = db.collection("Events");

        // Create a query to find the document based on the field value
        collectionReference.whereEqualTo("Event Name", fieldValue)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Get the document reference
                            DocumentReference documentRef = collectionReference.document(document.getId());

                            // Update the array using array union
                            documentRef.update("Participants", FieldValue.arrayUnion(newItem))
                                    .addOnSuccessListener(aVoid -> {
                                        // Array updated successfully
                                        Log.d(TAG, "Array updated successfully!");
                                    })
                                    .addOnFailureListener(e -> {
                                        // Handle failure
                                        Log.w(TAG, "Error updating array", e);
                                    });
                        }
                    } else {
                        // Handle failure to fetch the document
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public interface ValueExistsCallback {
        void onValueExists(boolean exists);
    }

    public void valueExists(String eventname, String fieldName, String desiredValue, final ValueExistsCallback callback) {
        eventRef.whereEqualTo("Event Name", eventname)
                .whereArrayContains(fieldName, desiredValue)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean exists = !task.getResult().isEmpty();
                            callback.onValueExists(exists);
                        } else {
                            callback.onValueExists(false);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void addUserfeedback(String feedback, String eventname, String whichOne, String username){
        eventRef.whereEqualTo("Event Name", eventname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Update the document based on the found document ID
                                String documentId = document.getId();
                                DocumentReference documentRef = eventRef.document(documentId);

                                // Create a map with the fields you want to update
                                Map<String, Object> updateMap = new HashMap<>();
                                Map<String, Object> updateMap2 = new HashMap<>();
                                updateMap.put(whichOne, updateMap2.put(username, feedback));
                                // Add other fields as needed

                                // Update the document with the new values
                                documentRef.update(updateMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Document successfully updated
                                                Log.d(TAG, "Document updated successfully!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle errors
                                                Log.w(TAG, "Error updating document", e);
                                            }
                                        });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void updateEvent(String fieldValue,  Map<String, Object> val) {
        // Assuming you have a reference to your Firestore database
        CollectionReference collectionReference = db.collection("Events");

        // Create a query to find the document based on the field value
        collectionReference.whereEqualTo("Event Name", fieldValue)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Get the document reference
                            DocumentReference documentRef = collectionReference.document(document.getId());

                            // Update the array using array union
                            documentRef.update(val)
                                    .addOnSuccessListener(aVoid -> {
                                        // Array updated successfully
                                        Log.d(TAG, "Array updated successfully!");
                                    })
                                    .addOnFailureListener(e -> {
                                        // Handle failure
                                        Log.w(TAG, "Error updating array", e);
                                    });
                        }
                    } else {
                        // Handle failure to fetch the document
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public void deleteEvent(String fieldValue) {
        // Assuming you have a reference to your Firestore database
        CollectionReference collectionReference = db.collection("Events");

        // Create a query to find the document based on the field value
        collectionReference.whereEqualTo("Event Name", fieldValue)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Get the document reference
                            DocumentReference documentRef = collectionReference.document(document.getId());

                            // Update the array using array union
                            documentRef.delete()
                                    .addOnSuccessListener(aVoid -> {
                                        // Array updated successfully
                                        Log.d(TAG, "Array updated successfully!");
                                    })
                                    .addOnFailureListener(e -> {
                                        // Handle failure
                                        Log.w(TAG, "Error updating array", e);
                                    });
                        }
                    } else {
                        // Handle failure to fetch the document
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }






}
