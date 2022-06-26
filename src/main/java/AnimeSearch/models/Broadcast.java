
package AnimeSearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Broadcast {

    @SerializedName("day")
    @Expose
    private Object day;
    @SerializedName("time")
    @Expose
    private Object time;
    @SerializedName("timezone")
    @Expose
    private Object timezone;
    @SerializedName("string")
    @Expose
    private Object string;

    public Object getDay() {
        return day;
    }

    public void setDay(Object day) {
        this.day = day;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public Object getTimezone() {
        return timezone;
    }

    public void setTimezone(Object timezone) {
        this.timezone = timezone;
    }

    public Object getString() {
        return string;
    }

    public void setString(Object string) {
        this.string = string;
    }

}
