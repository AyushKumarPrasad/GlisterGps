package limited.pvt.global.stachi.glisteruser.app.Model;

public class TimeState
{
    private String SavedTime , SavedDate ;

    public TimeState() {
    }

    public TimeState(String savedTime , String savedDate) {
        SavedTime = savedTime;
        SavedDate = savedDate;
    }

    public String getSavedTime() {
        return SavedTime;
    }

    public void setSavedTime(String savedTime) {
        SavedTime = savedTime;
    }

    public String getSavedDate() {
        return SavedDate;
    }

    public void setSavedDate(String savedDate) {
        SavedDate = savedDate;
    }
}
