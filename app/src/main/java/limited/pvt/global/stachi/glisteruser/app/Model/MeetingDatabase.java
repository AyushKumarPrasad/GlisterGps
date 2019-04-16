package limited.pvt.global.stachi.glisteruser.app.Model;

public class MeetingDatabase
{
    private String MeetingName ;
    private String MeetingDate ;
    private String MeetingStartTime ;
    private String MeetingEndTime ;
    private String MeetingMobile ;

    private String MeetingImage ;
    private String MeetingUserName ;
    private String MeetingUserPhone ;
    private String MeetingAddress ;
    private String MeetingUserLat ;
    private String MeetingUserLng ;
    private String MeetingOrder ;

    public MeetingDatabase() {
    }

    public MeetingDatabase(String meetingName, String meetingDate, String meetingStartTime, String meetingEndTime, String meetingMobile,
                           String meetingImage, String meetingUserName, String meetingUserPhone , String meetingAddress,
                           String meetingUserLat , String meetingUserLng , String meetingOrder)
    {
        MeetingName = meetingName;
        MeetingDate = meetingDate;
        MeetingStartTime = meetingStartTime;
        MeetingEndTime = meetingEndTime;
        MeetingMobile = meetingMobile;
        MeetingImage = meetingImage;
        MeetingUserName = meetingUserName;
        MeetingUserPhone = meetingUserPhone;
        MeetingAddress = meetingAddress;
        MeetingUserLat = meetingUserLat;
        MeetingUserLng = meetingUserLng ;
        MeetingOrder = meetingOrder ;
    }

    public String getMeetingName() {
        return MeetingName;
    }

    public void setMeetingName(String meetingName) {
        MeetingName = meetingName;
    }

    public String getMeetingDate() {
        return MeetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        MeetingDate = meetingDate;
    }

    public String getMeetingStartTime() {
        return MeetingStartTime;
    }

    public void setMeetingStartTime(String meetingStartTime) {
        MeetingStartTime = meetingStartTime;
    }

    public String getMeetingEndTime() {
        return MeetingEndTime;
    }

    public void setMeetingEndTime(String meetingEndTime) {
        MeetingEndTime = meetingEndTime;
    }

    public String getMeetingMobile() {
        return MeetingMobile;
    }

    public void setMeetingMobile(String meetingMobile) {
        MeetingMobile = meetingMobile;
    }

    public String getMeetingAddress() {
        return MeetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        MeetingAddress = meetingAddress;
    }

    public String getMeetingImage() {
        return MeetingImage;
    }

    public void setMeetingImage(String meetingImage) {
        MeetingImage = meetingImage;
    }

    public String getMeetingUserName() {
        return MeetingUserName;
    }

    public void setMeetingUserName(String meetingUserName) {
        MeetingUserName = meetingUserName;
    }

    public String getMeetingUserPhone() {
        return MeetingUserPhone;
    }

    public void setMeetingUserPhone(String meetingUserPhone) {
        MeetingUserPhone = meetingUserPhone;
    }

    public String getMeetingUserLat() {
        return MeetingUserLat;
    }

    public void setMeetingUserLat(String meetingUserLat) {
        MeetingUserLat = meetingUserLat;
    }

    public String getMeetingUserLng() {
        return MeetingUserLng;
    }

    public void setMeetingUserLng(String meetingUserLng) {
        MeetingUserLng = meetingUserLng;
    }

    public String getMeetingOrder() {
        return MeetingOrder;
    }

    public void setMeetingOrder(String meetingOrder) {
        MeetingOrder = meetingOrder;
    }
}
