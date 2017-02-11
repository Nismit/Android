package ca.ciccc.simplerss.net;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observable;

import ca.ciccc.simplerss.rss.FeedParser;

public class BackgroundTask extends Observable {
    // For Debug
    private static final String TAG = "BackgroundTask";

    private static final int READ_TIMEOUT_MS = 15000; // Mili Sec = 15 Sec
    private static final int CONNECTION_TIMEOUT_MS = 10000; // Mili Sec = 10 Sec
    private String url;
    ArrayList<?> rssFeeds;


    public enum Event {
        CONNECT, FINISH,
    }

    public void taskStart(String url) {
        setChanged();
        notifyObservers(Event.CONNECT);
        parseURL(url);
        if(!this.url.equals("")) {
            HttpConnection connection = new HttpConnection();
            connection.execute(this.url);
        }else {
            Log.d(TAG, "URL is null");
        }
    }

    //
    // Parse URL
    // If user input does not contain "http(s)://"
    // Add prefix protocol
    //
    private String parseURL(String url) {
        this.url = url;
        return this.url;
    }

    //
    // Set Rss Feeds
    //
    public void setRssFeeds(ArrayList<?> rssFeeds) {
        this.rssFeeds = rssFeeds;
        setChanged();
        notifyObservers(Event.FINISH);
    }

    //
    // Get Rss Feeds
    //
    public ArrayList<?> getRssFeeds() {
        return this.rssFeeds;
    }


    class HttpConnection extends AsyncTask<String, Object, ArrayList<?>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // HTTP Connection
        // RSSParser
        // return onPostExe
        @Override
        protected ArrayList<?> doInBackground(String... params) {

            ArrayList<?> tempRssFeeds = new ArrayList<>();

            try {
                final URL urlAddress = new URL(params[0]);
                InputStream stream = null;

                try {
                    Log.d(TAG, "Streaming: " + urlAddress);
                    stream = getHTTPStream(urlAddress);
                    tempRssFeeds = updateRSSFeed(stream);
                } finally {
                    if(stream != null) {
                        stream.close();
                    }
                }
            }catch (MalformedURLException e) {
                Log.e(TAG, "Feed URL is malformed", e);
            }catch (IOException e) {
                Log.e(TAG, "Error reading from network: " + e.toString());
            }catch (XmlPullParserException e) {
                e.printStackTrace();
            }catch (ParseException e) {
                e.printStackTrace();
            }

            return tempRssFeeds;
        }

        // Spinner
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        // Result
        // Get rss data
        @Override
        protected void onPostExecute(ArrayList<?> tempRssFeeds) {
            //super.onPostExecute();
            Log.d(TAG, "Set result");
            setRssFeeds(tempRssFeeds);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public ArrayList<?> updateRSSFeed(final InputStream stream) throws IOException, XmlPullParserException, ParseException {
        final FeedParser feedParser = new FeedParser();
        Log.d(TAG, "Parse Feed");
        final ArrayList<?> entries = (ArrayList<?>) feedParser.parse(stream);

        return entries;
    }

    private InputStream getHTTPStream(final URL urlAddress) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) urlAddress.openConnection();
        conn.setReadTimeout(READ_TIMEOUT_MS);
        conn.setConnectTimeout(CONNECTION_TIMEOUT_MS);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}