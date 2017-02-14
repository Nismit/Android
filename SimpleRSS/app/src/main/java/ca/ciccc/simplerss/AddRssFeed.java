package ca.ciccc.simplerss;

import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ca.ciccc.simplerss.db.DataBaseHandler;
import ca.ciccc.simplerss.fragments.FeedListView;
import ca.ciccc.simplerss.net.BackgroundTask;

public class AddRssFeed extends AppCompatActivity {
    // For Debug
    private static final String TAG = "RSS-AddRssFeed";

    BackgroundTask task = new BackgroundTask();
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rss_feed);
    }

    public void addData(View v) {
        //DataBaseHandler handler = new DataBaseHandler();
        Log.d(TAG, "Adding the data to Database");
        finish();
    }

    public void fetchData(View v) {
        Log.d(TAG, "Execute fetch data");
        task.addObserver(observer);
        task.taskStart("http://android-developers.blogspot.com/atom.xml");
        //task.taskStart("https://www.smashingmagazine.com/feed/");
        Log.d(TAG, "Another thread working for getting connection");
    }

    private void setResultView(ArrayList<?> rssFeeds) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArrayList", (ArrayList<? extends Parcelable>) rssFeeds);

        Log.d(TAG, "Creating fragment");
        FeedListView fragment = new FeedListView();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentPreview, fragment);
        fragmentTransaction.commit();
    }

    private Observer observer = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
        if(!(arg instanceof BackgroundTask.Event)) {
            return;
        }

        switch ((BackgroundTask.Event)arg) {
            case CONNECT:
                // Progress
                Log.d(TAG, "Observer starts connection http");
                break;
            case FINISH:
                // Done
                Log.d(TAG, "Observer ends");
                setResultView(task.getRssFeeds());
                break;
        }
        }
    };
}
