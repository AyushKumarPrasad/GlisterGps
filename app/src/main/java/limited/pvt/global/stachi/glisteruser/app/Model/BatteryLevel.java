package limited.pvt.global.stachi.glisteruser.app.Model;

public class BatteryLevel
{
    private String BatteryName ;
    private String BatteryPhone ;
    private String BatteryLevel ;
    private String BatteryScale ;
    private String BatteryPercentage ;

    public BatteryLevel() {
    }

    public BatteryLevel(String batteryName, String batteryPhone, String batteryLevel, String batteryScale, String batteryPercentage) {
        BatteryName = batteryName;
        BatteryPhone = batteryPhone;
        BatteryLevel = batteryLevel;
        BatteryScale = batteryScale;
        BatteryPercentage = batteryPercentage;
    }

    public String getBatteryName() {
        return BatteryName;
    }

    public void setBatteryName(String batteryName) {
        BatteryName = batteryName;
    }

    public String getBatteryPhone() {
        return BatteryPhone;
    }

    public void setBatteryPhone(String batteryPhone) {
        BatteryPhone = batteryPhone;
    }

    public String getBatteryLevel() {
        return BatteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        BatteryLevel = batteryLevel;
    }

    public String getBatteryScale() {
        return BatteryScale;
    }

    public void setBatteryScale(String batteryScale) {
        BatteryScale = batteryScale;
    }

    public String getBatteryPercentage() {
        return BatteryPercentage;
    }

    public void setBatteryPercentage(String batteryPercentage) {
        BatteryPercentage = batteryPercentage;
    }
}
