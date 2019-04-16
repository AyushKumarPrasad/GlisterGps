package limited.pvt.global.stachi.glisteruser.app.Notification;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;

public class MyFirebaseIdService extends FirebaseInstanceIdService
{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String tokenRefreshed = FirebaseInstanceId.getInstance().getToken();
        if (Common.currentUser != null)
                updateTokenToFirebase(tokenRefreshed);
    }

    private void updateTokenToFirebase(String tokenRefreshed)
    {
        String name = Common.currentUser.getName();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");
        Token token = new Token (tokenRefreshed , false , name );
        tokens.child(Common.currentUser.getPhone()).setValue(token);
    }
}
