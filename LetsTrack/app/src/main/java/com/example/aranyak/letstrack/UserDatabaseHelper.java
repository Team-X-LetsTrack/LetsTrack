package com.example.aranyak.letstrack;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranyak on 03-Nov-17.
 */

public class UserDatabaseHelper {
    Map<String, Object> user;
// Access a Cloud Firestore instance from your Activity

    FirebaseFirestore db;

    private final String TAG = "";

    public UserDatabaseHelper() {
        this.user = new HashMap<>();
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(Primary_User u) {
        user.put("Email", u.getEmail_ID());
        user.put("Phone Verified", u.isPhone_verified());
        user.put("Phone number", u.getP().getPhone());
        // Add a new document with a generated ID
        db.collection("users")
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
