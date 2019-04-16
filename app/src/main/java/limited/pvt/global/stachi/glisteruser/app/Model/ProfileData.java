package limited.pvt.global.stachi.glisteruser.app.Model;

public class ProfileData
{
    private String AttendanceDate ;
    private String AttendancePhone ;
    private String AttendanceName ;
    private String AttendanceTime ;
    private String AttendanceStatus ;
    private String AttendanceLatitude ;
    private String AttendanceLongitude ;
    private String AttendanceAddress ;
    private String AttendanceTargetArea ;

    public ProfileData() {
    }

    public ProfileData(String attendanceDate, String attendancePhone, String attendanceName, String attendanceTime, String attendanceStatus    ,
                     String attendanceLatitude, String attendanceLongitude , String attendanceAddress , String attendanceTargetArea  ) {
        AttendanceDate = attendanceDate;
        AttendancePhone = attendancePhone;
        AttendanceName = attendanceName;
        AttendanceTime = attendanceTime;
        AttendanceStatus = attendanceStatus;
        AttendanceLatitude = attendanceLatitude;
        AttendanceLongitude = attendanceLongitude;
        AttendanceAddress = attendanceAddress;
        AttendanceTargetArea = attendanceTargetArea ;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        AttendanceDate = attendanceDate;
    }

    public String getAttendancePhone() {
        return AttendancePhone;
    }

    public void setAttendancePhone(String attendancePhone) {
        AttendancePhone = attendancePhone;
    }

    public String getAttendanceName() {
        return AttendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        AttendanceName = attendanceName;
    }

    public String getAttendanceTime() {
        return AttendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        AttendanceTime = attendanceTime;
    }

    public String getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        AttendanceStatus = attendanceStatus;
    }

    public String getAttendanceLatitude() {
        return AttendanceLatitude;
    }

    public void setAttendanceLatitude(String attendanceLatitude) {
        AttendanceLatitude = attendanceLatitude;
    }

    public String getAttendanceLongitude() {
        return AttendanceLongitude;
    }

    public void setAttendanceLongitude(String attendanceLongitude) {
        AttendanceLongitude = attendanceLongitude;
    }

    public String getAttendanceAddress() {
        return AttendanceAddress;
    }

    public void setAttendanceAddress(String attendanceAddress) {
        AttendanceAddress = attendanceAddress;
    }

    public String getAttendanceTargetArea() {
        return AttendanceTargetArea;
    }

    public void setAttendanceTargetArea(String attendanceTargetArea) {
        AttendanceTargetArea = attendanceTargetArea;
    }
}


