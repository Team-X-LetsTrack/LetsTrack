package Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aranyak.Application.PhoneVerification;
import com.example.aranyak.Application.Primary_User;
import com.example.aranyak.Application.R;

public class Verify_phone extends AppCompatActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    EditText editTextCode;
    Button buttonCodeSubmit;

    SharedPreferences saved_value;
    SharedPreferences shared_pref;

    Primary_User current_user;

    static Verify_phone verify_phone;

    public static Verify_phone getInstance() {
        return verify_phone;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        verify_phone = this;

        editTextCode = (EditText) findViewById(R.id.editTextCode);

        buttonCodeSubmit = (Button) findViewById(R.id.buttonCodeSubmit);

        buttonCodeSubmit.setOnClickListener(this);

        saved_value = getSharedPreferences("Current_User", MODE_PRIVATE);

        Register.getInstance().finish();

    }

    public void verify_phone() {
        final String code = editTextCode.getText().toString().trim();
        if (code.isEmpty())
            Toast.makeText(this, "Enter code", Toast.LENGTH_SHORT);
        else {
            Primary_User current_user = new Primary_User(saved_value.getString("Email", ""), saved_value.getString("Contact_No.", ""), saved_value.getString("Password", ""));

            String c = saved_value.getString("Code", "");

            PhoneVerification p = new PhoneVerification(saved_value.getString("Contact_No.", ""));
            p.setCode(c);
            current_user.verifyPhone(p.verifyCode(code));

            if (current_user.isPhone_verified()) {
                Toast.makeText(this, "Phone Verified", Toast.LENGTH_SHORT);
                startActivity(new Intent(this, MainActivity.class));     //Change to main activity
            } else
                Toast.makeText(this, "Incorrect code", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onClick(View v) {
        verify_phone();
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor edit = saved_value.edit();
        edit.putString("Email", current_user.getEmail_ID());
        edit.putString("Contact_No", current_user.getContact_Number());
        edit.putBoolean("Phone_verified", current_user.isPhone_verified());

        edit.commit();
        super.onPause();
    }

}
