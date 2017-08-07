package richie.ee.com.leaveamessage;

/**
 * Created by richie on 8/6/17.
 */

public class Message {

    private String message;
    private double lat;


    private double lon;
    //Add time later

    public Message(){
        message="";
        lat=0;
        lon=0;
    }
    public Message(String message, double lat, double lon){
        this.message=message;
        this.lat=lat;
        this.lon=lon;
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

}
