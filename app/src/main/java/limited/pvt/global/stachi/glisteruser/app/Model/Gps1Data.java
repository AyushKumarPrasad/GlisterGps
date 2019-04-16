package limited.pvt.global.stachi.glisteruser.app.Model;

public class Gps1Data
{
    private String phone , lat, lng , name;

    public Gps1Data() {
    }

    public Gps1Data(String phone, String lat, String lng , String name) {
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
