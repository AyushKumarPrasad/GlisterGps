package limited.pvt.global.stachi.glisteruser.app.Model;

public class MeetingData
{
    private String RetailerName ;
    private String Date ;
    private String StartTime ;
    private String EndTime ;
    private String Mobile ;
    private String Address ;
    private String CommonTime ;

    public MeetingData() {
    }

    public MeetingData(String retailerName, String date, String startTime, String endTime, String mobile, String address, String commonTime) {
        RetailerName = retailerName;
        Date = date;
        StartTime = startTime;
        EndTime = endTime;
        Mobile = mobile;
        Address = address;
        CommonTime = commonTime;
    }

    public String getRetailerName() {
        return RetailerName;
    }

    public void setRetailerName(String retailerName) {
        RetailerName = retailerName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCommonTime() {
        return CommonTime;
    }

    public void setCommonTime(String commonTime) {
        CommonTime = commonTime;
    }
}
