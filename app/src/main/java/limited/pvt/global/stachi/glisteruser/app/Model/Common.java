package limited.pvt.global.stachi.glisteruser.app.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import limited.pvt.global.stachi.glisteruser.app.Notification.APIService;
import limited.pvt.global.stachi.glisteruser.app.Notification.RetrofitClient;


public class Common
{
    public static User currentUser ;

    private static final String BASE_URL = "https://fcm.googleapis.com/" ;

    public static String PHONE_TEXT = "userPhone" ;

    public static APIService getFCMService()
    {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static String convertCodeToStatus(String status)
    {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "Present";
        else if (status.equals("2"))
            return "Half-Day";
        else
            return "Absent";
    }

    public static final String DELETE = "Delete" ;
    public static final String USER_KEY = "User" ;
    public static final String PWD_KEY = "Password" ;

    public static boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null)
        {
            NetworkInfo [] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
            {
                for (int i = 0 ; i < info.length; i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false ;
    }
}
