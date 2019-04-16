package limited.pvt.global.stachi.glisteruser.app.Model;

/**
 * Created by Administrator on 17-05-2017.
 */

public class Show_Chat_Conversation_Data_Items {
    private String message;
    private String sender;
    private String orderid ;

    public Show_Chat_Conversation_Data_Items()
    {
    }

    public Show_Chat_Conversation_Data_Items(String message, String sender , String orderid) {
        this.message = message;
        this.sender = sender;
        this.orderid = orderid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
