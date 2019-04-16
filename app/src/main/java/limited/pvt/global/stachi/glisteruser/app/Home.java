package limited.pvt.global.stachi.glisteruser.app;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import limited.pvt.global.stachi.glisteruser.app.Gps.LocationReceiver;
import limited.pvt.global.stachi.glisteruser.app.Gps.LocationUpdatesService;
import limited.pvt.global.stachi.glisteruser.app.Gps.Utils;
import limited.pvt.global.stachi.glisteruser.app.Model.BatteryLevel;
import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Notification.Token;

public class Home extends AppCompatActivity
{
    private Button butGps , secondMap , showAttendance , meetingDetails , productItems;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1001;
    private LocationReceiver rcvMReceiver;
    private LocationUpdatesService mLUService;
    private LocationReceiver myReceiver;
    private boolean mBound = false;

    FirebaseDatabase database;
    DatabaseReference requests;

    private Context mContext;
    private TextView mTextViewInfo;
    private TextView mTextViewPercentage;
    private ProgressBar mProgressBar;

    private int mProgressStatus = 0;
    int scale ;
    int level ;
    float percentage ;

    String batteyLevel ;
    String batteryScale ;
    String batteryPercentage ;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            mTextViewInfo.setText("Battery Scale : " + scale);

            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            mTextViewInfo.setText(mTextViewInfo.getText() + "\nBattery Level : " + level);

            percentage = level/ (float) scale;
            mProgressStatus = (int)((percentage)*100);

            mTextViewPercentage.setText("" + mProgressStatus + "%");
            mTextViewInfo.setText(mTextViewInfo.getText() + "\nPercentage : " + mProgressStatus + "%");

            mProgressBar.setProgress(mProgressStatus);

            batteyLevel = String.valueOf(level);
            batteryScale = String.valueOf(scale);
            batteryPercentage = String.valueOf(percentage);

            BatteryLevel batteryLevel = new BatteryLevel
                    (
                            Common.currentUser.getName(),
                            Common.currentUser.getPhone(),
                            batteyLevel,
                            batteryScale,
                            batteryPercentage
                    );

            requests.setValue(batteryLevel);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final String phone = Common.currentUser.getName() ;

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Battery").child(phone);

        meetingDetails = (Button)findViewById(R.id.meetingDetailsHome);

        productItems = (Button) findViewById(R.id.productListItem);
        productItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , ProductItems.class);
                startActivity(intent);
            }
        });

        butGps = (Button) findViewById(R.id.butGps);
        showAttendance = (Button)findViewById(R.id.button2);


        butGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,MessageList.class);
                startActivity(intent);
            }
        });

        showAttendance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home.this,GetAttendance.class);
                startActivity(intent);
            }
        });

        if(!checkPermission())
        {
            requestPermissions();
        }

        updateToken(FirebaseInstanceId.getInstance().getToken()) ;

        meetingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Meeting.class);
                startActivity(intent);
            }
        });

        mContext = getApplicationContext();

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        mContext.registerReceiver(mBroadcastReceiver,iFilter);

        mTextViewInfo = (TextView) findViewById(R.id.tv_info);
        mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);

        mProgressBar = (ProgressBar) findViewById(R.id.pb);

    }

    private void updateToken(String token)
    {
        String name = Common.currentUser.getName() ;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");
        Token data = new Token (token , false , name);
        tokens.child(Common.currentUser.getPhone()).setValue(data);
    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Close Glister")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Home.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkPermission())
        {
            if(mLUService == null)
                mLUService = new LocationUpdatesService();

            myReceiver = new LocationReceiver();

            secondMap = (Button) findViewById(R.id.secondMap);

            secondMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (!checkPermission())
                    {
                        requestPermissions();
                    }

                    else
                    {
                        mLUService.requestLocationUpdates();
                        Intent intent = new Intent(Home.this, Attendance.class);
                        startActivity(intent);
                    }
                }
            });

            changeButtonsState(Utils.requestingLocationUpdates(this));

            bindService(new Intent(this, LocationUpdatesService.class), mServiceConection,
                    Context.BIND_AUTO_CREATE);
            LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                    new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
        }
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (mBound) {

            unbindService(mServiceConection);
            mBound = false;
        }

        super.onStop();
    }

    private boolean checkPermission()
    {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void requestPermissions()
    {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(shouldProvideRationale)
        {
            Snackbar.make(
                    findViewById(R.id.main_layout),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(Home.this,
                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        }
        else
            ActivityCompat.requestPermissions(Home.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length <= 0)
                {

                }
                else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(mLUService == null)
                        mLUService = new LocationUpdatesService();
                }
                else
                {
                    changeButtonsState(false);
                    Snackbar.make(
                            findViewById(R.id.main_layout),
                            R.string.permission_denied_explanation,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.settings, new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    Intent intent = new Intent();
                                    intent.setAction(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",
                                            BuildConfig.APPLICATION_ID, null);
                                    intent.setData(uri);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
        }
    }

    private void changeButtonsState(boolean requestingLocationUpdates)
    {
        if (requestingLocationUpdates)
        {
            secondMap.setEnabled(true);
        }

        else
        {
            secondMap.setEnabled(true);
        }
    }

    private final ServiceConnection mServiceConection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLUService = ((LocationUpdatesService.LocationBinder)service).getLocationUpdateService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mLUService = null;
            mBound = false;
        }
    };
}