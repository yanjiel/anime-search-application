
package AnimeSearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class To {

    @SerializedName("day")
    @Expose
    //private Object day;
    private Integer day;
    @SerializedName("month")
    @Expose
    //private Object month;
    private Integer month;
    @SerializedName("year")
    @Expose
    //private Object year;
    private Integer year;

//    public Object getDay() {
//        return day;
//    }
//
//    public void setDay(Object day) {
//        this.day = day;
//    }
//
//    public Object getMonth() {
//        return month;
//    }
//
//    public void setMonth(Object month) {
//        this.month = month;
//    }
//
//    public Object getYear() {
//        return year;
//    }
//
//    public void setYear(Object year) {
//        this.year = year;
//    }

    public Integer getDay() {return day;}

    public void setDay(Integer day) { this.day = day;}

    public Integer getMonth() { return month;}

    public void setMonth(Integer month) {this.month = month;}

    public Integer getYear() {return year;}

    public void setYear(Integer year) {this.year = year;}
}
