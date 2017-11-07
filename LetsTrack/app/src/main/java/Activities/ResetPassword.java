package Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aranyak.Application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    EditText currentpass;
    EditText newpass;
    EditText confirmnewpass;

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        currentpass = (EditText) findViewById(R.id.editTextCurrentPassword);
        newpass = (EditText) findViewById(R.id.editTextNewPassword);
        confirmnewpass = (EditText) findViewById(R.id.editTextConfirmNewPassword);

        confirm = (Button) findViewById(R.id.buttonConfirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();
            }
        });
    }

    private void changepassword() {
        String oldpass = currentpass.getText().toString().trim();
        final String newpassword = newpass.getText().toString().trim();
        String confirmnewpassword = confirmnewpass.getText().toString().trim();

        if (oldpass.isEmpty())
            Toast.makeText(this, "Enter current password", Toast.LENGTH_SHORT).show();
        else if (newpassword.isEmpty())
            Toast.makeText(this, "Enter new password", Toast.LENGTH_SHORT).show();
        else if (confirmnewpassword.isEmpty())
            Toast.makeText(this, "Confirm new password field empty", Toast.LENGTH_SHORT).show();
        else {
            if (newpassword.compareTo(confirmnewpassword) != 0)
                Toast.makeText(this, "New password does not match confirm password field", Toast.LENGTH_SHORT).show();
            else {
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                AuthCredential reauth = EmailAuthProvider.getCredential(email, oldpass);
                FirebaseAuth.getInstance().getCurrentUser().reauthenticate(reauth).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().getCurrentUser().updatePassword(newpassword);
                        }
                    }
                });
            }
        }
    }

}
