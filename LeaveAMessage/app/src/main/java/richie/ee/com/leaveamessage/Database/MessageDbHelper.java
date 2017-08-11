package richie.ee.com.leaveamessage.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import richie.ee.com.leaveamessage.Database.MessageContract.MessageTable;


/**
 * Created by Richie on 8/10/2017.
 */

public class MessageDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "letterapp.db";

    public MessageDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MessageTable.TABLE_NAME + " (" +
                    MessageTable._ID + " INTEGER PRIMARY KEY," +
                    MessageTable.COLUMN_NAME_ONLINE_ID + " INTEGER," +
                    MessageTable.COLUMN_NAME_LAT + " INTEGER," +
                    MessageTable.COLUMN_NAME_LON + " INTEGER," +
                    MessageTable.COLUMN_NAME_LETTER+ " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MessageTable.TABLE_NAME;
}
