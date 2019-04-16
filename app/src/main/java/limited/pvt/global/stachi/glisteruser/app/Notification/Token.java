package limited.pvt.global.stachi.glisteruser.app.Notification;

public class Token
{
    private String token ;
    private boolean isServerToken ;

    private String nameToken ;


    public Token() {
    }

    public Token(String token, boolean isServerToken , String nameToken  ) {
        this.token = token;
        this.isServerToken = isServerToken;
        this.nameToken = nameToken ;
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

    public String getNameToken() {
        return nameToken;
    }

    public void setNameToken(String nameToken) {
        this.nameToken = nameToken;
    }
}