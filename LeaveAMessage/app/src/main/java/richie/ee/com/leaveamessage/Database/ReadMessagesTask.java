package richie.ee.com.leaveamessage.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import richie.ee.com.leaveamessage.Database.MessageContract.MessageTable;

import java.util.ArrayList;
import java.util.List;

import richie.ee.com.leaveamessage.Message;

/**
 * Created by Richie on 8/17/2017.
 */

public class ReadMessagesTask extends AsyncTask<Void,Void,List<Message>> {

    private Context mContext;
    private List<Message> mMessages;
    public ReadMessagesTask (Context context){
        mContext = context;
    }
    public List<Message>getMessages(){
        return mMessages;
    }

    @Override
    protected List<Message> doInBackground(Void... voids) {

        List<Message> messages = new ArrayList<>();
        MessageDbHelper dbHelper = new MessageDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                MessageTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );


        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(MessageTable._ID));
            double lat = cursor.getDouble(cursor.getColumnIndex(MessageTable.COLUMN_NAME_LAT));
            double lon = cursor.getDouble(cursor.getColumnIndex(MessageTable.COLUMN_NAME_LON));
            String letter = cursor.getString(cursor.getColumnIndex(MessageTable.COLUMN_NAME_LETTER));
            Message message = new Message(id,lat,lon,letter);
            messages.add(message);
        }
        cursor.close();

        return messages;
    }

    @Override
    protected void onPostExecute(List<Message> messages) {
        super.onPostExecute(messages);
        if(messages!=null) {
            mMessages.clear();
            mMessages.addAll(messages);
        }


    }
}
