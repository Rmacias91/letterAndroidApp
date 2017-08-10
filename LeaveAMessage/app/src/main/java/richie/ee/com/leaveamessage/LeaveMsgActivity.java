package richie.ee.com.leaveamessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import richie.ee.com.leaveamessage.WebUtility.postMessageTask;

/**
 * Created by Richie on 8/8/2017.
 */

public class LeaveMsgActivity extends AppCompatActivity {
    private EditText mEditMessage;
    private Button mButSubmit;
    private Button mButCancel;
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
                    double lat = 24;//GPS Location will replace this is a test.
                    double lon = 19;
                    Message message = new Message(lat,lon,messageString);
                    postMessage(message);
                }
            }
        });

    }

    private void postMessage(Message message){
        Message[]messageParams = {message};
        postMessageTask postTask = new postMessageTask();
        postTask.execute(messageParams);
    }
}
