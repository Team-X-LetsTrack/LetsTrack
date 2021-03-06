package Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aranyak.Application.Primary_User;
import com.example.aranyak.Application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ButtonSignIn;

    private EditText EditTextEmail;
    private EditText EditTextPassword;

    private TextView TextViewRegister;
    private TextView TextViewForgotPassword;

    private ProgressDialog ProgressDialog;

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

        //       mAuth = FirebaseAuth.getInstance();
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

        Intent signin = getIntent();
        Primary_User us = (Primary_User) signin.getSerializableExtra("User");
        if (us.getEmail_ID().equals(email)) {
            us.attempt_signin(email, password);
        } else {
            /*Get rid of next two lines after database is set up and replace it with retrieving user information from database*/
            String contact = " ";
            Primary_User user = new Primary_User(email, contact, password);
            us = user;
            user.attempt_signin(email, password);
        }

        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();

        if (u != null) {
            ProgressDialog.dismiss();
            if (u.isEmailVerified() && us.isPhone_verified()) {
                Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show();
                //Start new activity
            } else
                Toast.makeText(this, "Verify Email ID!", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog.dismiss();
            Toast.makeText(this, "Sign in Failed!", Toast.LENGTH_SHORT).show();
        }

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
