package limited.pvt.global.stachi.glisteruser.app.Gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.widget.Toast;

import limited.pvt.global.stachi.glisteruser.app.Attendance;

/**
 * Created by Ayush Jain on 8/31/17.
 */

public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
        if (location != null)
        {
            Toast.makeText(context, Utils.getLocationText(location),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
