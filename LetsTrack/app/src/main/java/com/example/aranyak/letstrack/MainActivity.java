package com.example.aranyak.letstrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ButtonSignIn;
    private EditText EditTextEmail, EditTextPassword;
    private TextView TextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonSignIn=(Button) findViewById(R.id.buttonSignIn);
        EditTextEmail=(EditText)findViewById(R.id.editTextEmail);
        EditTextPassword=(EditText)findViewById(R.id.editTextPassword);
        TextViewRegister=(TextView)findViewById(R.id.textViewSignUp);

    }

    private void Signin()
    {
        
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
        }
    }
}
