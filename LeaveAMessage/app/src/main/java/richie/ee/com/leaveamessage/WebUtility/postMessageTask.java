package richie.ee.com.leaveamessage.WebUtility;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import richie.ee.com.leaveamessage.Message;

/**
 * Created by richie on 8/7/17.
 */

public class postMessageTask extends AsyncTask<Message, Void, Void> {
    private final String LOG_TAG = getAllMessagesTask.class.getSimpleName();

    @Override
    protected Void doInBackground(Message... message) {

        Message postMessage =message[0];
        String lat = String.valueOf(postMessage.getLat());
        String lon = String.valueOf(postMessage.getLon());
        String letter = postMessage.getMessage();

        try {
            URL url = new URL("http://172.31.99.97:3000/");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("Lat", lat)
                    .appendQueryParameter("Lon", lon)
                    .appendQueryParameter("Letter", letter);
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            //TODO Handle Exception error when we Can't connect to DB
            conn.connect();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with Connecting", e);
        }
    return null;
    }
}
