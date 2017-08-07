package richie.ee.com.leaveamessage.WebUtility;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import richie.ee.com.leaveamessage.Message;

/**
 * Created by Richie on 8/6/2017..
 */

public class WebUtility extends AsyncTask<String,Void,List<Message>>{
    private final String LOG_TAG = WebUtility.class.getSimpleName();

    @Override
    protected List<Message> doInBackground(String... strings) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String messagesJsonString;

        try{
            final String MESSAGES_BASE_URL=
                    "http://192.168.1.8:3000/Message";
            //Build the Universal Resource Identifier
            Uri builtUri = Uri.parse(MESSAGES_BASE_URL).buildUpon().build();

            //Open the Connection

            URL  url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Create input Stream and Buffer
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = reader.readLine())!=null){
                Log.d(LOG_TAG,line+"\n");
                buffer.append(line+"\n");
            }
            messagesJsonString = buffer.toString();
        }catch(IOException e){
            Log.e(LOG_TAG,"Error",e);
            return null;
        }finally {
            if(urlConnection!= null){
                urlConnection.disconnect();
            }
            if(reader!=null) {
                try {
                    reader.close();
                }catch (final IOException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        }
       // try{
        //JSONPARSE
            return null;
        //}catch (JSONException e){
         //   Log.e(LOG_TAG,e.getMessage(),e);
         //   e.printStackTrace();
        //}


    }
}
