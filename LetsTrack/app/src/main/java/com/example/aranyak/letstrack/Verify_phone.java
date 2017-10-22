package com.example.aranyak.letstrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verify_phone extends AppCompatActivity implements View.OnClickListener {

    EditText editTextCode;
    Button buttonCodeSubmit;

    SharedPreferences saved_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        editTextCode = (EditText) findViewById(R.id.editTextCode);

        buttonCodeSubmit = (Button) findViewById(R.id.buttonCodeSubmit);

        buttonCodeSubmit.setOnClickListener(this);

        saved_value = getSharedPreferences("Current_User", MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {
        final String code = editTextCode.getText().toString().trim();
        if (code.isEmpty())
            Toast.makeText(this, "Enter code", Toast.LENGTH_SHORT);
        else {
            Primary_User current_user = new Primary_User(saved_value.getString("Email", ""), saved_value.getString("Contact_No.", ""), saved_value.getString("Password", ""));

            String c = saved_value.getString("Code", "");

            PhoneVerification p = new PhoneVerification(saved_value.getString("Contact_No.", ""));
            p.setCode(c);
            current_user.verifyPhone(p.verifyCode(code));

            startActivity(new Intent(this, MainActivity.class));
        }
    }


}
