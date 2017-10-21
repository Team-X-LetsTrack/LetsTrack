package com.example.aranyak.letstrack;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public class Primary_User extends User {

    ArrayList<Contact> Contact_Array;
    ArrayList<Label> Label_Array;
    Label Current_Label;
    String Password;

    private static String TAG;

    private FirebaseAuth mAuth;

    public Primary_User(String email_ID, String contact_Number, String password) {
        super(email_ID, contact_Number);
        Password = password;

        Label_Array = new ArrayList<Label>();
        Label home = new Label();
        home.setLabel_name("Home");
        Label_Array.add(home);

        Label work = new Label();
        home.setLabel_name("Work");
        Label_Array.add(work);

        Label Gym = new Label();
        Gym.setLabel_name("Gym");
        Label_Array.add(Gym);

        Label Vacation = new Label();
        Vacation.setLabel_name("Vacation");
        Label_Array.add(Vacation);

        Label others = new Label();
        others.setLabel_name("Others");
        Label_Array.add(others);


        Contact_Array = new ArrayList<Contact>();

        mAuth = FirebaseAuth.getInstance();

    }

    public void attempt_register() {

        mAuth.createUserWithEmailAndPassword(Email_ID, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Registration Successful");
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                    PhoneVerification p = new PhoneVerification(Contact_Number);
                    p.sendVerificationtext();
                } else {
                    Log.d(TAG, "Registration Failed");
                }
            }
        });

    }

    public void attempt_signin() {
        mAuth.signInWithEmailAndPassword(Email_ID, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
            }
        });

    }
    public void addContact(Contact c) {

    }


}
