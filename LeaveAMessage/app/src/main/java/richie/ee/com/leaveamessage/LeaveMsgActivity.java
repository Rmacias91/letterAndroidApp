package richie.ee.com.leaveamessage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import richie.ee.com.leaveamessage.Database.SaveMessagesTask;
import richie.ee.com.leaveamessage.WebUtility.postMessageTask;

/**
 * Created by Richie on 8/8/2017.
 */

public class LeaveMsgActivity extends AppCompatActivity {
    private static final String LOG_TAG =LeaveMsgActivity.class.getSimpleName();
    private EditText mEditMessage;
    private Button mButSubmit;
    private Button mButCancel;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private double lat;
    private double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_msg);

        mEditMessage = (EditText) findViewById(R.id.edit_Message);

        mButCancel = (Button) findViewById(R.id.but_cancel);
        mButCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mButSubmit = (Button) findViewById(R.id.but_submit);
        mButSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mEditMessage.getText())) {
                    String messageString = mEditMessage.getText().toString();
                    lat = 0;//GPS Location will replace this is a test.
                    lon = 0;
                    Message message = new Message(lat,lon,messageString);
                    postMessage(message);
                }
            }
        });

        //GPS---**
        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener mLocationListener = new LocationListener(){
            public void onLocationChanged(Location location){
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.d(LOG_TAG,"Lat is: "+ lat);
                Log.d(LOG_TAG,"Lon is: "+ lon);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        }

    }

    private void postMessage(Message message){
        Message[]messageParams = {message};
       //HOLD OFF ON POSTING TO DB ONLINE
        // postMessageTask postTask = new postMessageTask();
       // postTask.execute(messageParams);
        //POST TO LOCAL DB
        SaveMessagesTask saveMessagesTaskLocaldb = new SaveMessagesTask(this);
        saveMessagesTaskLocaldb.execute(messageParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(mLocationListener);
    }
}
