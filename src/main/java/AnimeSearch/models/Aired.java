
package AnimeSearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Aired {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    //private Object to;
    private String to;
    @SerializedName("prop")
    @Expose
    private Prop prop; //prop.from, prop.to
    @SerializedName("string")
    @Expose
    private String string;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

//    public Object getTo() {
//        return to;
//    }
//
//    public void setTo(Object to) {
//        this.to = to;
//    }


    public String getTo() {return to;}

    public void setTo(String to) {this.to = to;}

    public Prop getProp() {
        return prop;
    }

    public void setProp(Prop prop) {
        this.prop = prop;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
