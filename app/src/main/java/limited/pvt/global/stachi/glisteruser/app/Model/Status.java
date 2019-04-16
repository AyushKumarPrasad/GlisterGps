package limited.pvt.global.stachi.glisteruser.app.Model;

public class Status
{
    private String emailId , statusId ;

    public Status() {
    }

    public Status(String emailId, String statusId) {
        this.emailId = emailId;
        this.statusId = statusId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
