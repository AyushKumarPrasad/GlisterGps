package limited.pvt.global.stachi.glisteruser.app;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import limited.pvt.global.stachi.glisteruser.app.Gps.LocationUpdatesService;
import limited.pvt.global.stachi.glisteruser.app.Gps.Utils;
import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.Gps1Data;
import limited.pvt.global.stachi.glisteruser.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteruser.app.Model.TimeState;
import limited.pvt.global.stachi.glisteruser.app.Model.Upload;
import limited.pvt.global.stachi.glisteruser.app.Notification.APIService;
import limited.pvt.global.stachi.glisteruser.app.Notification.MyResponse;
import limited.pvt.global.stachi.glisteruser.app.Notification.Notification;
import limited.pvt.global.stachi.glisteruser.app.Notification.Sender;
import limited.pvt.global.stachi.glisteruser.app.Notification.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Attendance extends AppCompatActivity
{
    TextView DisplayTimeIn12format , saveTime , saveDate ;
    Button AccessTime , leaveApply ;
    private static final String TAG = "MainActivity";
    private EditText mEditTextFileName , targetArea;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    FirebaseDatabase database;
    DatabaseReference requests;
    DatabaseReference attendanceTime;
    APIService mService ;
    String time ;
    String dates ;
    private static final int CAMERA_REQUEST_CODE = 1 ;
    private ImageView mImageView ;
    private StorageReference mStorage ;
    private DatabaseReference mDatabaseRef;

//////////////////      //////////////////      //////////////////      //////////////////      //////////////////      //////////////////

    private TextView textView ;
    private DatabaseReference getUserDataReference ;
    String lat  ;
    String lng ;
    Double latitude , longitude ;
    private TextView mTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        mImageView = (ImageView) findViewById(R.id.imageview);

        targetArea = (EditText) findViewById(R.id.tvtargetArea);

        mService = Common.getFCMService();

        final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());
        final String phone = Common.currentUser.getPhone() ;

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Attendance");
        attendanceTime = database.getReference("TimeState").child(phone);

        String ayush = Common.currentUser.getName();
        mStorage = FirebaseStorage.getInstance().getReference("uploads/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Images").child(ayush);

        DisplayTimeIn12format = (TextView)findViewById(R.id.textView1);
        mEditTextFileName = (EditText) findViewById(R.id.tvDate);
        AccessTime = (Button)findViewById(R.id.button1);
        leaveApply= (Button)findViewById(R.id.leaveApply);

        leaveApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance.this , LeaveApply.class);
                startActivity(intent);
            }
        });

        saveTime = (TextView)findViewById(R.id.textView2);
        saveDate = (TextView)findViewById(R.id.textView3);

        mEditTextFileName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mEditTextFileName.setText(date);
            }
        });

        final String targetAreaUser = targetArea.getText().toString();

//////////////////      //////////////////      //////////////////      //////////////////      //////////////////      //////////////////

        getUserDataReference = FirebaseDatabase.getInstance().getReference("PermanentGps").child(Common.currentUser.getPhone());

        textView = (TextView) findViewById(R.id.text_address);
        mTextAddress = (TextView) findViewById(R.id.text_address_geolocation);

        getUserDataReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    lat = dataSnapshot.child("lat").getValue().toString();
                    lng = dataSnapshot.child("lng").getValue().toString();

                    textView.setText("lat :" + String.valueOf(lat) + "\n" + "lng :" + String.valueOf(lng));

                    latitude = Double.parseDouble(lat);
                    longitude = Double.parseDouble(lng);
                }

                Geocoder gcd = new Geocoder(Attendance.this, Locale.getDefault());
                List<Address> addresses = null;
                try
                {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0)
                    {
                        // If any additional address line present than only, check with max available address lines by
                        // getMaxAddressLineIndex()

                        final String address = addresses.get(0).getAddressLine(0);
                        final String city = addresses.get(0).getLocality();
                        final String state = addresses.get(0).getAdminArea();
                        final String country = addresses.get(0).getCountryName();
                        final String postalCode = addresses.get(0).getPostalCode();
                        final String knownName = addresses.get(0).getFeatureName();

                        mTextAddress.setText(address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName);

                        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000)
                        {
                            public void onTick(long millisUntilFinished)
                            {
                                final Calendar c = Calendar.getInstance();
                                DisplayTimeIn12format.setText
                                        (c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));

                                final int hour = Integer.parseInt(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
                                final int minute = Integer.parseInt(String.valueOf(c.get(Calendar.MINUTE)));
                                final int second = Integer.parseInt(String.valueOf(c.get(Calendar.SECOND)));

                                AccessTime.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        AccessTime.setEnabled(false);
                                        if (mEditTextFileName.getText().length() > 4 )
                                        {
                                            //   (hour >= 6 && hour <= 17 )

                                            if (hour >= 6 && hour <= 23 )
                                            {
                                                ProfileData request = new ProfileData(
                                                        mEditTextFileName.getText().toString(),
                                                        Common.currentUser.getPhone(),
                                                        Common.currentUser.getName(),
                                                        DisplayTimeIn12format.getText().toString(),
                                                        "0",
                                                        lat,
                                                        lng,
                                                        address + " " + city + " " + state + " " + country + " "
                                                                + postalCode + " "+ knownName,
                                                        targetArea.getText().toString()
                                                );

                                                String order_number = String.valueOf(System.currentTimeMillis());
                                                String order_name = String.valueOf(Common.currentUser.getName());

                                                requests.child(order_number).setValue(request);
                                                sendNotificationOrder(order_number, order_name);


                                                final String userTime = DisplayTimeIn12format.getText().toString();
                                                attendanceTime.child("savedDate").setValue(mEditTextFileName.getText().toString()) ;
                                                attendanceTime.child("savedTime")
                                                        .setValue(userTime).addOnCompleteListener(new OnCompleteListener<Void>()
                                                {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if (task.isSuccessful())
                                                        {
                                                            Toast.makeText(Attendance.this, "Attendance Marked",
                                                                    Toast.LENGTH_SHORT).show();

                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(Attendance.this, "Attendance Failed ",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                                Toast.makeText(Attendance.this, "Attendance Marked",
                                                        Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                startActivityForResult(intent , CAMERA_REQUEST_CODE);

                                     //           mEditTextFileName.setText(null);

                                                AccessTime.setEnabled(false);


                                            }
                                            else
                                            {
                                                Toast.makeText(Attendance.this, "Attendance Time Lapsed",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(Attendance.this, "Select Date", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            public void onFinish()
                            {

                            }
                        };

                        newtimer.start();

                        getUserDataReference.keepSynced(true);
                    }
                    else
                    {
                        //         Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
         //           Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        }) ;

//////////////////      //////////////////      //////////////////      //////////////////      //////////////////      ////////////

        attendanceTime.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    time = dataSnapshot.child("savedTime").getValue().toString();
                    dates = dataSnapshot.child("savedDate").getValue().toString();

                    saveTime.setText(time);
                    saveDate.setText(dates);

                    String realDate = dates ;
                    String savedDate = date ;

                    if ( realDate.equals(savedDate))
                    {
                        AccessTime.setEnabled(false);
                        AccessTime.setBackgroundColor(getResources().getColor(R.color.message1));
                        AccessTime.setText("Attendance marked for today");
                        AccessTime.setTextColor(Color.WHITE);
                    }
                    else
                    {
                        AccessTime.setEnabled(true);
                    }

                }

                else
                {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        }) ;

        sendNotification();
    }

    private void sendNotification()
    {
        android.app.Notification.Builder builder = new android.app.Notification.Builder(this)
                .setSmallIcon(R.mipmap.logosmall)
                .setContentTitle("Mark attendance else half day will be considered ")
                .setContentText("Ignore it if you have already marked");

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this , Attendance.class);
        PendingIntent contnetIntent = PendingIntent.getActivity(this , 0 , intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contnetIntent);

        android.app.Notification notification = builder.build();
        notification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= android.app.Notification.DEFAULT_SOUND;

        manager.notify(new Random().nextInt() , notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            int notificationId = 1;
            String channelId = "channel-01";
            String channelName = "Channel Name";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.logosmall)
                    .setContentTitle("Mark attendance else half day will be considered ")
                    .setContentText("Ignore it if you have already marked");

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            mBuilder.setContentIntent(resultPendingIntent);

            notificationManager.notify(notificationId, mBuilder.build());
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
        {
            final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);

            String kumar = Common.currentUser.getName();

            final String path = "Attendance/" + System.currentTimeMillis() + kumar;
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            final byte[] data1 = baos.toByteArray();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            StorageReference reference = storageReference.child(path);

            UploadTask uploadTask = reference.putBytes(data1);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Toast.makeText(Attendance.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(
                            Common.currentUser.getName(),
                            taskSnapshot.getDownloadUrl().toString(),
                            mEditTextFileName.getText().toString(),
                            DisplayTimeIn12format.getText().toString()
                    );

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                    mEditTextFileName.setText(null);

                    String emailName = Common.currentUser.getName() ;
                    final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());

                    String[] TO = {"satachiglobal@gmail.com"};

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Attendance marked by " + emailName + " for date " + date );
                    emailIntent.putExtra(Intent.EXTRA_TEXT,"attendance time : " + DisplayTimeIn12format.getText().toString() );
                    emailIntent.putExtra(Intent.EXTRA_STREAM , data1);
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {

                }
            });

            if (mImageView != null)
            {

            }
        }
    }

    private void sendNotificationOrder(final String order_number , final  String order_name)
    {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query data = tokens.orderByChild("serverToken").equalTo(true);
        data.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnspshot : dataSnapshot.getChildren())
                {
                    Token serverToken = postSnspshot.getValue(Token.class);

                    Notification notification = new Notification("Glister" , "Attendance marked by " + order_name);

                    Sender content = new Sender(serverToken.getToken(), notification);
                    mService.sendNotification(content)
                            .enqueue(new Callback<MyResponse>()
                            {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response)
                                {
                                    if (response.code() == 200)
                                    {
                                        if (response.body().success == 1)
                                        {
                                            Toast.makeText(Attendance.this,"Attendance Marked",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(Attendance.this,"Attendance Failed !",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t)
                                {
                                    Log.e("ERROR" , t.getMessage());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}

