package richie.ee.com.leaveamessage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import richie.ee.com.leaveamessage.Database.ReadMessagesTask;
import richie.ee.com.leaveamessage.WebUtility.getAllMessagesTask;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Button mButNearBy;
    Button mButLeaveMsg;
    ArrayList<Message> mMessages;//Keep as a List?

    @Override
    public void onStart(){
        super.onStart();
        updateMessages();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMessages = new ArrayList<>();
        mButNearBy = (Button) findViewById(R.id.NearBy_But);
        mButNearBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NearByList.class);
                Bundle bundle = new Bundle();
                if(mMessages != null) {
                    bundle.putParcelableArrayList("nearByList",mMessages);//Should I cast as an Array List
                    i.putExtras(bundle);
                }
                startActivity(i);
            }
        });
        mButLeaveMsg = (Button) findViewById(R.id.LeaveMsg_But);
        mButLeaveMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LeaveMsgActivity.class);
                startActivity(i);
            }
        });

        //Ask for Permission for GPS location Services
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },23);
        }
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },24);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMessages(){
        //Pull from Local DB
        ReadMessagesTask readMessagesTaskLocalDb = new ReadMessagesTask(this,this);
        readMessagesTaskLocalDb.execute();

        //PULL FROM ONLINE DB
        /*
        getAllMessagesTask getAllMessagesTaskTask = new getAllMessagesTask();
        getAllMessagesTaskTask.execute();
        List<Message>updatedMessages = getAllMessagesTaskTask.getMessages();
        if(updatedMessages!= null) {
            mMessages.clear();
            mMessages.addAll(updatedMessages);
        }
        */

        //mAdapter.notifyDataSetChanged(); NEED TO RUN FOR ADAPTER WHEN IN USE(MAYBE IN NEARBYLIST CLASS)
    }
    public void setList(List<Message> localDbMessages){
        if(localDbMessages!=null) {
            mMessages.clear();
            mMessages.addAll(localDbMessages);
            Log.d(LOG_TAG,"MESSAGES STORED IN LOCAL DB");
            Log.d(LOG_TAG, "Size is: "+ mMessages.size());
            for(Message message: mMessages){
                Log.d(LOG_TAG,message.getMessage());
                Log.d(LOG_TAG,String.valueOf(message.getLat()));
                Log.d(LOG_TAG,String.valueOf(message.getLon()));
            }
        }
    }
}
