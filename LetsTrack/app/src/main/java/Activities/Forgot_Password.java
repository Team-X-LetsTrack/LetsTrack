package Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aranyak.Application.R;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    private Button ButtonResetPassword;

    private EditText EditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        ButtonResetPassword = (Button) findViewById(R.id.buttonResetPassword);
        EditTextEmail = (EditText) findViewById(R.id.editTextEmail);

        ButtonResetPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //Search database for email id
        //Fetch password
        //Sign into firebase using email and password
        //send reset password email
    }
}
