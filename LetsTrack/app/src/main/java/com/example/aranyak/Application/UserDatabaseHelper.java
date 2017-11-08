package com.example.aranyak.Application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranyak on 03-Nov-17.
 */

public class UserDatabaseHelper {
    static Map<String, Object> user;
// Access a Cloud Firestore instance from your Activity

    static FirebaseFirestore db;

    static String file_name;

    private static final String TAG = " ";

    public UserDatabaseHelper() {
        this.user = new HashMap<>();
        db = FirebaseFirestore.getInstance();
    }

    public static void Writetofile(Context context, Primary_User u) {
        file_name = FirebaseAuth.getInstance().getUid();
        File file = new File(context.getFilesDir(), file_name);

        file.setWritable(true);
        try {
            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(u.getEmail_ID());
            writer.newLine();
            writer.write(u.getContact_Number());
            writer.newLine();
            ArrayList<Label> labels = u.getLabel_Array();
            for (int i = 0; i < 4; i++) {
                writer.write(labels.get(i).getLabel_name() +
                        " " + labels.get(i).getLocation().latitude +
                        " " + labels.get(i).getLocation().longitude);
                writer.newLine();
            }
            ArrayList<Contact> contacts = u.getContact_Array();
            for (int i = 0; i < 5; i++) {
                writer.write(contacts.get(i).getUserName() +
                        " " + contacts.get(i).getContact_Number() +
                        " " + contacts.get(i).getEmail_ID() +
                        " " + contacts.get(i).getCurrent_Label() +
                        " " + contacts.get(i).getContact_status());
                writer.newLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        file.setWritable(false);
    }

    public static void writeSharedPreference() {

    }

    public static void addUser(Primary_User u) {
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
