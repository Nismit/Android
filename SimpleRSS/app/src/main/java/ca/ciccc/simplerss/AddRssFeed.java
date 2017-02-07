package ca.ciccc.simplerss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ca.ciccc.simplerss.net.HttpClient;
import ca.ciccc.simplerss.rss.AtomFeedParser;

public class AddRssFeed extends AppCompatActivity {
    // For Debug
    private static final String TAG = "RSS-AddRssFeed";

    HttpClient client = new HttpClient();
    EditText urlText;
    TextView fetchResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rss_feed);
        fetchResultView = (TextView) findViewById(R.id.fetchResultView);
    }

    public void fetchData(View v) {
        Log.d(TAG, "Execute fetch data");
        client.addObserver(observer);
        client.openConnection("http://android-developers.blogspot.com/atom.xml");
        Log.d(TAG, "Another thread working for getting connection");
    }

    private void setResultView(ArrayList<?> rssFeeds) {
        AtomFeedParser.Entry entry = (AtomFeedParser.Entry) rssFeeds.get(0);
        fetchResultView.setText("ID: "+ entry.id + "\nTitle: "+ entry.title);
    }

    private Observer observer = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            if(!(arg instanceof HttpClient.Event)) {
                return;
            }

            switch ((HttpClient.Event)arg) {
                case CONNECT:
                    // Progress
                    Log.d(TAG, "Observer starts connection http");
                    break;
                case FINISH:
                    // Done
                    Log.d(TAG, "Observer ends");
                    setResultView(client.getRssFeeds());
                    break;

            }
        }
    };
}
