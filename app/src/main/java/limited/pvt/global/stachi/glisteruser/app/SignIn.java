package limited.pvt.global.stachi.glisteruser.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;
import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.User;

public class SignIn extends AppCompatActivity
{
    EditText edtPhone, edtPassword ;
    Button btnSignIn ;
    com.rey.material.widget.CheckBox ckbRemember ;
    FirebaseDatabase database ;
    DatabaseReference table_user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        ckbRemember = (CheckBox) findViewById(R.id.chkRemember);
        Paper.init(this);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!edtPhone.getText().toString().isEmpty()  &&  !edtPassword.getText().toString().isEmpty())
                {
                    if (Common.isConnectedToInternet(getBaseContext()))
                    {
                        if (ckbRemember.isChecked())
                        {
                            Paper.book().write(Common.USER_KEY, edtPhone.getText().toString());
                            Paper.book().write(Common.PWD_KEY, edtPassword.getText().toString());
                        }

                        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                        mDialog.setMessage("Please Wait...");
                        mDialog.show();

                        table_user.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                                {
                             //       String DeviceToken = FirebaseInstanceId.getInstance().getToken();

                                    String android_id = Settings.Secure.getString(SignIn.this.getContentResolver(),
                                            Settings.Secure.ANDROID_ID);

                                    mDialog.dismiss();

                                    User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                    user.setPhone(edtPhone.getText().toString());
                                    if (user.getPassword().equals(edtPassword.getText().toString()) &&
                                            user.getDeviceId().equals(android_id))
                                    {
                                        Intent intent = new Intent(SignIn.this, Home.class);
                                        Common.currentUser = user;
                                        startActivity(intent);
                                        finish();

                                        table_user.removeEventListener(this);
                                    }
                                    else
                                        {
                                        Toast.makeText(SignIn.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                    {
                                    mDialog.dismiss();
                                    Toast.makeText(SignIn.this, "User not exist !", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError)
                            {

                            }
                        });
                    }
                    else
                        {
                        Toast.makeText(SignIn.this, "Check Your Connection", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else
                {
                    Toast.makeText(SignIn.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}