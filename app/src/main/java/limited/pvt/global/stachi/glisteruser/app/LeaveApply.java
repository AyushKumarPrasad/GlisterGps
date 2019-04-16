package limited.pvt.global.stachi.glisteruser.app;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.LeaveDatabase;

public class LeaveApply extends AppCompatActivity
{
    EditText typeLeave ;
    Button sendLeave ;
    TextView showTime ;
    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apply);

        final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());
        final String userName = Common.currentUser.getName() ;

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        final String currentDateTimeString = sdf.format(d);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Leave");

        typeLeave = (EditText) findViewById(R.id.leaveEdit);
        sendLeave = (Button) findViewById(R.id.btnLeave);

        sendLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = typeLeave.getText().toString().toLowerCase() ;

                if (! TextUtils.isEmpty(message))
                {
                    LeaveDatabase leaveDatabase = new LeaveDatabase
                            (
                                    date,
                                    currentDateTimeString,
                                    userName,
                                    typeLeave.getText().toString().toLowerCase()
                            );

                    String order_number = String.valueOf(System.currentTimeMillis());

                    requests.child(order_number).setValue(leaveDatabase);

                    Toast.makeText(LeaveApply.this , "Message Sent" , Toast.LENGTH_SHORT).show();

                    typeLeave.setText(null);

                    String emailName = Common.currentUser.getName() ;
                    final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());

                    String[] TO = {"satachiglobal@gmail.com"};

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    //   emailIntent.setType("*/*");
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Leave applied by : " + emailName + " for date " + date  );
                    emailIntent.putExtra(Intent.EXTRA_TEXT,"Leave Message : " + message );
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                }

                else
                {
                    Toast.makeText(LeaveApply.this , "Give Detailed Reasons" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

