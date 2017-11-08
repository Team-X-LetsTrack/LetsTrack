package com.example.aranyak.Application;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public class Primary_User extends User implements Account {

    public ArrayList<Contact> getContact_Array() {
        return Contact_Array;
    }

    ArrayList<Contact> Contact_Array;
    ArrayList<Label> Label_Array;

    public Label getCurrent_Label() {
        return Current_Label;
    }

    Label Current_Label;
    String Password;

    boolean phone_verified;
    PhoneVerification p;

    private static String TAG;

    private static FirebaseAuth mAuth;

    public Primary_User(String email_ID, String contact_Number, String password) {
        super(email_ID, contact_Number);
        Password = password;
        p = new PhoneVerification(Contact_Number);
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

    public ArrayList<Label> getLabel_Array() {
        return Label_Array;
    }

    public static void ResetPassword(final Activity activity, String email) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(activity, "Email for resetting password sent", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity, "Unable to send email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void attempt_register() {

        mAuth.createUserWithEmailAndPassword(Email_ID, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Registration Successful");
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                    p.sendVerificationtext();
                } else {
                    Log.d(TAG, "Registration Failed");
                }
            }
        });

    }

    public void attempt_signin(String email, String Password) {
        mAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
            }
        });

    }

    public void verifyPhone(boolean b) {
        phone_verified = b;
    }

    public boolean isPhone_verified() {
        return phone_verified;
    }

    public void addContact(Contact c) {
        //TODO add logic to add contact
    }

    public String getPassword() {
        return Password;
    }

    public PhoneVerification getP() {
        return p;
    }

}
