package limited.pvt.global.stachi.glisteruser.app.Notification;

public class NotificationToken
{
    private String token ;
    private boolean isServerToken ;

    public NotificationToken () {
    }

    public NotificationToken (String token, boolean isServerToken ) {
        this.token = token;
        this.isServerToken = isServerToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isServerToken() {
        return isServerToken;
    }

    public void setServerToken(boolean serverToken) {
        isServerToken = serverToken;
    }
}
