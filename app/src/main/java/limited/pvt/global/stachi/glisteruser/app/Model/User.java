package limited.pvt.global.stachi.glisteruser.app.Model;


public class User
{
    private String Name ;
    private String Password;
    private String Phone;
    private String IsStaff ;
    private String DeviceId ;
    private String Address ;
    private String Aadharcard ;
    private String Pancard ;
    private String Emergencynumber ;
    private String Email ;
    private String Refname ;
    private String Refnumber ;
    private String Userimage ;

    public User() {
    }

    public User(String name, String password, String phone , String deviceId , String address , String aadharcard , String pancard ,
                String emergencynumber , String email , String refname , String refnumber , String userimage )
    {
        Name = name;
        Password = password;
        Phone = phone ;
        IsStaff = "false";
        DeviceId = deviceId ;

        Address = address;
        Aadharcard = aadharcard;
        Pancard = pancard;
        Emergencynumber = emergencynumber;
        Email = email;
        Refname = refname;
        Refnumber = refnumber;
        Userimage = userimage;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAadharcard() {
        return Aadharcard;
    }

    public void setAadharcard(String aadharcard) {
        Aadharcard = aadharcard;
    }

    public String getPancard() {
        return Pancard;
    }

    public void setPancard(String pancard) {
        Pancard = pancard;
    }

    public String getEmergencynumber() {
        return Emergencynumber;
    }

    public void setEmergencynumber(String emergencynumber) {
        Emergencynumber = emergencynumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRefname() {
        return Refname;
    }

    public void setRefname(String refname) {
        Refname = refname;
    }

    public String getRefnumber() {
        return Refnumber;
    }

    public void setRefnumber(String refnumber) {
        Refnumber = refnumber;
    }

    public String getUserimage() {
        return Userimage;
    }

    public void setUserimage(String userimage) {
        Userimage = userimage;
    }
}