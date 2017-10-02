package com.example.aranyak.letstrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ButtonSignIn;

    private EditText EditTextEmail;
    private EditText EditTextPassword;

    private TextView TextViewRegister;
    private TextView TextViewForgotPassword;

    private ProgressDialog ProgressDialog;

    private FirebaseAuth mAuth;

    private static String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonSignIn=(Button) findViewById(R.id.buttonSignIn);

        EditTextEmail=(EditText)findViewById(R.id.editTextEmail);
        EditTextPassword=(EditText)findViewById(R.id.editTextPassword);

        TextViewRegister=(TextView)findViewById(R.id.textViewSignUp);
        TextViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);

        ButtonSignIn.setOnClickListener(this);
        TextViewRegister.setOnClickListener(this);
        TextViewForgotPassword.setOnClickListener(this);

        ProgressDialog=new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
    }



    private void Signin()
    {
        String email=EditTextEmail.getText().toString().trim();
        String password=EditTextPassword.getText().toString().trim();

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

        ProgressDialog.setMessage("Signing In... ");
        ProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ProgressDialog.dismiss();
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Sign in Failed!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Sign in Successful!",
                                    Toast.LENGTH_SHORT).show();
                            // Start profile Activity here
                        }


                    }
                });

    }
    @Override
    public void onClick(View view)
    {
        if(view==ButtonSignIn)
        {
            //Sign in
            Signin();
        }
        else if(view==TextViewRegister)
        {
            //Start Register Activity
            startActivity(new Intent(this,Register.class));
        } else if (view == TextViewForgotPassword) {
            //start Forgot Password Activity
            startActivity(new Intent(this, Forgot_Password.class));
        }
    }
}
