package richie.ee.com.leaveamessage.WebUtility;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import richie.ee.com.leaveamessage.Message;

/**
 * Created by Richie on 8/6/2017..
 */

public class getAllMessagesTask extends AsyncTask<String,Void,List<Message>>{
    private final String LOG_TAG = getAllMessagesTask.class.getSimpleName();
    List<Message> mMessages = new ArrayList<>();

    public List<Message> getMessages(){
        return mMessages;
    }

    @Override
    protected List<Message> doInBackground(String... typeOfRequest) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String messagesJsonString;

        try{
            final String MESSAGES_BASE_URL=
                    "http://172.31.99.97:3000/Message";
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
        }catch( IOException e){
            Log.e(LOG_TAG,"Error with Connecting",e);
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
       try{
        return getMovieListFromJSON(messagesJsonString);
        }catch (JSONException e){
            Log.e(LOG_TAG,e.getMessage(),e);
            e.printStackTrace();
        }

    return null;
    }

    @Override
    protected void onPostExecute(List<Message> messages) {
        super.onPostExecute(messages);
        if(messages!=null) {
            mMessages.clear();
            mMessages.addAll(messages);
            for (Message message : mMessages) {
                Log.v(LOG_TAG, message.getId() + " " + message.getLat() + " " + message.getLon()
                        + " " + message.getMessage() + "\n");
            }
        }


    }

    private List<Message> getMovieListFromJSON(String messageJsonString)
            throws JSONException{
        List<Message> messages = new ArrayList<>();
        JSONArray array = new JSONArray(messageJsonString);
        for(int i =0; i<array.length();i++){
            JSONObject jObj = array.getJSONObject(i);
            Long id = jObj.getLong("Id");
            double lat = jObj.getDouble("Lat");
            double lon = jObj.getDouble("Lon");
            String message= jObj.getString("Letter");
            messages.add(new Message(id,lat,lon,message));

        }

    return messages;

    }
}
