package ca.ciccc.simplerss;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ca.ciccc.simplerss.fragments.FeedListView;
import ca.ciccc.simplerss.net.BackgroundTask;

public class MainActivity extends AppCompatActivity {
    // For Debug
    private static final String TAG = "RSS-MainActivity";

    BackgroundTask task = new BackgroundTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //task.addObserver(observer);
        //task.taskStart("http://android-developers.blogspot.com/atom.xml");
        //task.taskStart("https://www.smashingmagazine.com/feed/");
        //Log.d(TAG, "Another thread working for getting connection");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(this, AddRssFeed.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    private void setResultView(ArrayList<?> rssFeeds) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArrayList", (ArrayList<? extends Parcelable>) rssFeeds);

        Log.d(TAG, "Creating fragment");
        FeedListView fragment = new FeedListView();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment);
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
