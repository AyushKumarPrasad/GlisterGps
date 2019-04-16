package limited.pvt.global.stachi.glisteruser.app.Model;

public class Upload
{
    private String AttendanceName ;
    private String AttendanceDate ;
    private String AttendanceTime ;
    private String AttendanceImage;

    public Upload() {
    }

    public Upload(String attendanceName, String attendanceImage , String attendanceDate , String attendanceTime) {
        AttendanceName = attendanceName;
        AttendanceImage = attendanceImage;
        AttendanceDate = attendanceDate;
        AttendanceTime = attendanceTime;
    }

    public String getAttendanceName() {
        return AttendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        AttendanceName = attendanceName;
    }

    public String getAttendanceImage() {
        return AttendanceImage;
    }

    public void setAttendanceImage(String attendanceImage) {
        AttendanceImage = attendanceImage;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        AttendanceDate = attendanceDate;
    }

    public String getAttendanceTime() {
        return AttendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        AttendanceTime = attendanceTime;
    }
}
