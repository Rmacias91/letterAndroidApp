package richie.ee.com.leaveamessage.Database;

import android.provider.BaseColumns;

/**
 * Created by Richie on 8/10/2017.
 */

public final class MessageContract {

    private MessageContract(){
    }

    public static final class MessageTable implements BaseColumns{
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_NAME_ONLINE_ID = "onlineId";
        public static final String COLUMN_NAME_LAT = "lat";
        public static final String COLUMN_NAME_LON = "lon";
        public static final String COLUMN_NAME_LETTER = "letter";

    }
}
