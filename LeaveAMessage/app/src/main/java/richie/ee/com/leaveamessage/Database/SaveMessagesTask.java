package richie.ee.com.leaveamessage.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import richie.ee.com.leaveamessage.Message;
import richie.ee.com.leaveamessage.Database.MessageContract.MessageTable;

/**
 * Created by richie on 8/10/17.
 */

public class SaveMessagesTask extends AsyncTask<Message,Void,Void> {

    private Context mContext;



    public SaveMessagesTask(Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Message... messages) {
        Message message = messages[0];
        MessageDbHelper messageDbHelper = new MessageDbHelper(mContext);
        SQLiteDatabase db = messageDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageTable.COLUMN_NAME_ONLINE_ID,message.getOnlineId());
        contentValues.put(MessageTable.COLUMN_NAME_LETTER,message.getMessage());
        contentValues.put(MessageTable.COLUMN_NAME_LAT,message.getLat());
        contentValues.put(MessageTable.COLUMN_NAME_LON,message.getLon());

        // Insert the new row, returning the primary key value of the new row
        db.insert(MessageTable.TABLE_NAME, null, contentValues);
        return null;
    }

}
