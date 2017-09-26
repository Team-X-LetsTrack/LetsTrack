package com.example.aranyak.letstrack;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private static String TAG;

    private Button ButtonRegister;

    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private EditText EditTextConfirmPassword;
    private EditText EditTextPhone;

    private ProgressDialog ProgressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButtonRegister=(Button) findViewById(R.id.buttonRegister);

        EditTextEmail=(EditText)findViewById(R.id.editTextEmail);
        EditTextPassword=(EditText)findViewById(R.id.editTextPassword);
        EditTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        EditTextPhone=(EditText)findViewById(R.id.editTextPhone);

        ProgressDialog=new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        ButtonRegister.setOnClickListener(this);
    }

    private void RegisterUser() {
        String email=EditTextEmail.getText().toString().trim();
        String password=EditTextPassword.getText().toString().trim();
        String Confirmpassword=EditTextConfirmPassword.getText().toString().trim();
        String phone=EditTextPhone.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email field is empty
            Toast.makeText(this,"Please enter an email ID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password)) {
            //password field is empty
            Toast.makeText(this,"Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Confirmpassword)||Confirmpassword!=password) {
            //Confirm password field is empty
            Toast.makeText(this,"Passwords do not match! Try again", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(phone)) {
            //Contact Number field is empty
            Toast.makeText(this,"Please enter a contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isDigitsOnly(phone)) {
            Toast.makeText(this,"Please enter a valid contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog.setMessage("Registering...");
        ProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registration Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Register.this, "Registration Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==ButtonRegister)
        {
            RegisterUser();
        }
    }
}
