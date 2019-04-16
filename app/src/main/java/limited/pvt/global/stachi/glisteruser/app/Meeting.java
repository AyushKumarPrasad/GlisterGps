package limited.pvt.global.stachi.glisteruser.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteruser.app.Model.MeetingImage;
import limited.pvt.global.stachi.glisteruser.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteruser.app.Model.Upload;

public class Meeting extends AppCompatActivity
{
    private Button mUploadBtn  ;
    private EditText Mname , Mdate , MstartTime , MendTime , Mmobile ,Maddress , Morder ;
    private TextView showTime ;
    private ImageView mImageView ;

    private static final int CAMERA_REQUEST_CODE = 1 ;
    private StorageReference mStorage ;
    private DatabaseReference mDatabaseRef ;
    private ProgressDialog mProgress  ;

/////////////////////////      //////////////////      //////////////////      //////////////////      //////////////////      ////////

    private TextView textView ;
    private DatabaseReference getUserDataReference ;
    String lat  ;
    String lng ;
    Double latitude , longitude ;
    private TextView mTextAddress;
    String address , city , state , country , postalCode , knownName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        String ayush = Common.currentUser.getName();

        mStorage = FirebaseStorage.getInstance().getReference("uploads/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("MeetingDetails").child(ayush);

        mUploadBtn = (Button) findViewById(R.id.uploadBtnMeetingDetails);

        Mname = (EditText) findViewById(R.id.meetingName);
        Mdate = (EditText) findViewById(R.id.meetingDate);
        MstartTime = (EditText) findViewById(R.id.meetingStartTime);
        MendTime = (EditText) findViewById(R.id.meetingEndTime);
        Mmobile = (EditText) findViewById(R.id.meetingMobile);
        Maddress = (EditText) findViewById(R.id.meetingAddress);
        Morder = (EditText) findViewById(R.id.meetingOrder);

        showTime = (TextView) findViewById(R.id.meetingShowTime);

        mImageView = (ImageView) findViewById(R.id.meetingImageview);

        final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());

        final Calendar c = Calendar.getInstance();

        Mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mdate.setText(date);
            }
        });

        MstartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);
                MstartTime.setText(currentDateTimeString);
            }
        });

        MendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);
                MendTime.setText(currentDateTimeString);
            }
        });

        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                final Calendar c = Calendar.getInstance();
                showTime.setText(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));

                final int hour = Integer.parseInt(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
                final int minute = Integer.parseInt(String.valueOf(c.get(Calendar.MINUTE)));
                final int second = Integer.parseInt(String.valueOf(c.get(Calendar.SECOND)));
            }
            public void onFinish()
            {

            }
        };

        newtimer.start();

        mUploadBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = Mname.getText().toString().toLowerCase().trim();
                String date = Mdate.getText().toString().trim();
                String startTime = MstartTime.getText().toString().trim();
                String endTime = MendTime.getText().toString().trim();
                String mobile = Mmobile.getText().toString().trim();
                String address = Maddress.getText().toString().toLowerCase().trim();
                String order = Morder.getText().toString().toLowerCase().trim();

                if (! TextUtils.isEmpty(name) && ! TextUtils.isEmpty(date) && ! TextUtils.isEmpty(startTime)
                        && ! TextUtils.isEmpty(mobile) && ! TextUtils.isEmpty(order))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent , CAMERA_REQUEST_CODE);
                }

                else
                {
                    Toast.makeText(Meeting.this,"Enter all details" , Toast.LENGTH_SHORT).show();
                }
            }
        });

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

                Geocoder gcd = new Geocoder(Meeting.this, Locale.getDefault());
                List<Address> addresses = null;
                try
                {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0)
                    {
                        // If any additional address line present than only, check with max available address lines by
                        // getMaxAddressLineIndex()

                        address = addresses.get(0).getAddressLine(0);
                        city = addresses.get(0).getLocality();
                        state = addresses.get(0).getAdminArea();
                        country = addresses.get(0).getCountryName();
                        postalCode = addresses.get(0).getPostalCode();
                        knownName = addresses.get(0).getFeatureName();

                        mTextAddress.setText(address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);

            String kumar = Common.currentUser.getName();

            String path = "Meeting/" + System.currentTimeMillis() + kumar;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            StorageReference reference = storageReference.child(path);

            UploadTask uploadTask = reference.putBytes(data1);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Toast.makeText(Meeting.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                    MeetingDatabase meetingDatabase = new MeetingDatabase(
                            Mname.getText().toString(),
                            Mdate.getText().toString(),
                            MstartTime.getText().toString(),
                            MendTime.getText().toString(),
                            Mmobile.getText().toString(),
                            taskSnapshot.getDownloadUrl().toString(),
                            Common.currentUser.getName(),
                            Common.currentUser.getPhone(),
                            address + " " + city + " " + state + " " + country + " "
                                    + postalCode + " "+ knownName,
                            lat,
                            lng,
                            Morder.getText().toString()
                    );

                    String order_number = String.valueOf(System.currentTimeMillis());

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(order_number).setValue(meetingDatabase);

                    String name = Mname.getText().toString() ;
                    String sTime = MstartTime.getText().toString();
                    String eTime = MendTime.getText().toString() ;
                    String mobile = Mmobile.getText().toString() ;
                    String address = Maddress.getText().toString() ;

                    String emailName = Common.currentUser.getName() ;
                    final String date = new SimpleDateFormat("yyyy-MM-dd-EEEE", Locale.getDefault()).format(new Date());

                    String[] TO = {"satachiglobal@gmail.com"};

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Meeting done by : " + emailName + " for date " + date );

                    emailIntent.putExtra(Intent.EXTRA_TEXT,"Retailer Name : " + name + "\n" +
                    "Meeting Start Time : " + sTime + "\n" + "Meeting End Time : " + eTime + "\n" + "Retaile Mobile : " + mobile
                    + "\n" + "Retailer Address : " + address);

                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();

                    Mname.setText(null);
                    Mdate.setText(null);
                    MstartTime.setText(null);
                    MendTime.setText(null);
                    Mmobile.setText(null);
                    Maddress.setText(null);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            if (mImageView != null)
            {

            }
        }
    }
}

/*
    mere dost acting hume aati nahi, naa kabhi kiya hai, hum woh hai jisne zamane ko bahut kuch diya hai,
    kuch kadam aaje jaane par log itraate hain , hum sab kuch seh kar bhi roz muskuraate hain,
    kamzoron ki tarah rang badalna hume aata nahi , kyunki yeh ek bimaari hai jo kabhi jaata nahi,
    ladkiyan bahut dekhi hai aur aage bhi dekhenge , CA aur Dentist muje kaise rok paayenge,
    dard aur dua har zindagi ki kahani hoti hai , jeevan mein vinamra raho pyaare yehi mahapursho ki nishani hoti hai,
    mahadev ka bhakt hu aaj kasam khata hu mahadev ki meri jeet ki mithaai sabse pehle aapko khilaunga.
 */