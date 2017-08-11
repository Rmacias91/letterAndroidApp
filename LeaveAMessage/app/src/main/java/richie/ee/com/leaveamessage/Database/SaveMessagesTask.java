package richie.ee.com.leaveamessage.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import richie.ee.com.leaveamessage.Message;

/**
 * Created by richie on 8/10/17.
 */

public class SaveMessagesTask extends AsyncTask<Message,Void,Void> {

    private Context mContext;

    public SaveMessagesTask(Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Message... params) {
        MessageDbHelper messageDbHelper = new MessageDbHelper(mContext);
        SQLiteDatabase db =
    }
}
