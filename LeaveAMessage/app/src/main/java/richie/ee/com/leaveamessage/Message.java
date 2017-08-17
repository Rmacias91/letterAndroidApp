package richie.ee.com.leaveamessage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by richie on 8/6/17.
 */

public class Message implements Parcelable{



    private int id;
    private int onlineId;
    private String message;
    private double lat;
    private double lon;
    //Add time later



    public Message(){
        message="";
        lat=0;
        lon=0;
    }
    public Message(double lat, double lon,String message){
        this.message=message;
        this.lat=lat;
        this.lon=lon;
    }

    public Message(int id, double lat, double lon,String message){
        this.id=id;
        this.message=message;
        this.lat=lat;
        this.lon=lon;
    }
    public Message(Parcel in){
        this.message= in.readString();
        this.lat= in.readDouble();
        this.lon = in.readDouble();
    }

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(message);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>(){
        public Message createFromParcel(Parcel in)
        {
            return new Message(in);
        }
        public Message[] newArray(int size)
        {
            return new Message[size];
        }
    };


    public double getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    public double getOnlineId(){return onlineId;}

    public void setOnlineId(int onlineId) {
        this.onlineId = onlineId;
    }
}
