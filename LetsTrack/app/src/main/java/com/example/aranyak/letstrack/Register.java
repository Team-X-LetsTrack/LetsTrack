package com.example.aranyak.letstrack;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        final String email = EditTextEmail.getText().toString().trim();
        final String password = EditTextPassword.getText().toString().trim();
        final String Confirmpassword = EditTextConfirmPassword.getText().toString().trim();
        final String phone = EditTextPhone.getText().toString().trim();

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
        else if(TextUtils.isEmpty(Confirmpassword)||!Confirmpassword.equals(password)) {
            //Confirm password field is empty
            Toast.makeText(this,"Passwords do not match! Try again", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(phone)) {
            //Contact Number field is empty
            Toast.makeText(this,"Please enter a contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!android.util.Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(this,"Please enter a valid contact number", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Please enter a valid email ID", Toast.LENGTH_SHORT).show();
            return;
        }

        
        ProgressDialog.setMessage("Registering...");
        ProgressDialog.show();


        Primary_User current_user = new Primary_User(email, phone, password);

        current_user.attempt_register();

        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();

        if (u != null) {
            ProgressDialog.dismiss();
            Toast.makeText(Register.this, "Registration Successful",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "createUserWithEmail:onComplete:" + true);

            u.sendEmailVerification();
            FirebaseAuth.getInstance().signOut();

        } else {
            ProgressDialog.dismiss();
            Toast.makeText(Register.this, "Registration unsuccessful",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "createUserWithEmail:onComplete:" + false);
        }

    }

    @Override
    public void onClick(View v) {
        RegisterUser();
    }
}
