package ca.ciccc.simplerss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddRssFeed extends AppCompatActivity {
    public static final String TAG = "AddRssFeed";
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rss_feed);
    }

    public void fetchData(View v) {
        //http://android-developers.blogspot.com/atom.xml
        //AtomFeedParser feed = new AtomFeedParser();
        parseURL();
    }

    private void parseURL() {
        urlText = (EditText) findViewById(R.id.urlText);

        try {
            String result = urlText.getText().toString();
            URL url = new URL(result);
            InputStream stream = null;

            try {
                stream = downloadURL(url);
                System.out.println(stream);
            }finally {
                stream.close();
            }
        }catch (MalformedURLException e) {
            Log.e(TAG, "Feed URL is malformed", e);
            return;
        }catch (IOException e) {
            Log.e(TAG, "Error reading from network: " + e.toString());
            return;
        }


    }

    private InputStream downloadURL(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000 /* milliseconds */);
        conn.setConnectTimeout(10000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }
}
