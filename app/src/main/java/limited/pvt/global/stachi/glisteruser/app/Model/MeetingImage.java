package limited.pvt.global.stachi.glisteruser.app.Model;

public class MeetingImage
{
    private String meetingImage ;
    private String meetingName ;
    private String meetingPhone ;
    private String meetingCommon ;

    public MeetingImage() {
    }

    public MeetingImage(String meetingImage, String meetingName, String meetingPhone, String meetingCommon) {
        this.meetingImage = meetingImage;
        this.meetingName = meetingName;
        this.meetingPhone = meetingPhone;
        this.meetingCommon = meetingCommon;
    }

    public String getMeetingImage() {
        return meetingImage;
    }

    public void setMeetingImage(String meetingImage) {
        this.meetingImage = meetingImage;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingPhone() {
        return meetingPhone;
    }

    public void setMeetingPhone(String meetingPhone) {
        this.meetingPhone = meetingPhone;
    }

    public String getMeetingCommon() {
        return meetingCommon;
    }

    public void setMeetingCommon(String meetingCommon) {
        this.meetingCommon = meetingCommon;
    }
}
