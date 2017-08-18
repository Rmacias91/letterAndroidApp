package richie.ee.com.leaveamessage.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import richie.ee.com.leaveamessage.Database.MessageContract.MessageTable;

import java.util.ArrayList;
import java.util.List;

import richie.ee.com.leaveamessage.MainActivity;
import richie.ee.com.leaveamessage.Message;

/**
 * Created by Richie on 8/17/2017.
 */

public class ReadMessagesTask extends AsyncTask<Void,Void,List<Message>> {
    private static final String LOG_TAG = "ReadMessageTask";

    private Context mContext;
    private MainActivity mMainActivity;
    public ReadMessagesTask (Context context, MainActivity activity){
        mContext = context;
        this.mMainActivity = activity;
    }

    @Override
    protected List<Message> doInBackground(Void... voids) {

        List<Message> messages = new ArrayList<>();
        MessageDbHelper dbHelper = new MessageDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor  cursor = db.rawQuery("select * from "+MessageTable.TABLE_NAME,null);


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
        mMainActivity.setList(messages);
    }
}
