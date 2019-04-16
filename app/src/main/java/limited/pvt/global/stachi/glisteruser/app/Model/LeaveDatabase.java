package limited.pvt.global.stachi.glisteruser.app.Model;

public class LeaveDatabase
{
    private String LeaveDate ;
    private String LeaveTime ;
    private String LeaveName ;
    private String LeaveMessage ;

    public LeaveDatabase() {
    }

    public LeaveDatabase(String leaveDate, String leaveTime, String leaveName, String leaveMessage) {
        LeaveDate = leaveDate;
        LeaveTime = leaveTime;
        LeaveName = leaveName;
        LeaveMessage = leaveMessage;
    }

    public String getLeaveDate() {
        return LeaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        LeaveDate = leaveDate;
    }

    public String getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        LeaveTime = leaveTime;
    }

    public String getLeaveName() {
        return LeaveName;
    }

    public void setLeaveName(String leaveName) {
        LeaveName = leaveName;
    }

    public String getLeaveMessage() {
        return LeaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        LeaveMessage = leaveMessage;
    }
}
