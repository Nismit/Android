package ca.ciccc.simplerss.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ciccc.simplerss.RssFeedDataModel;
import ca.ciccc.simplerss.adapters.CustomAdapter;
import ca.ciccc.simplerss.rss.RssFeed;

public class FeedListView extends ListFragment {
    // For Debug
    private static final String TAG = "RSS-FeedListView";

    // Variables
    ListView listView;

    // Custom ArrayList
    ArrayList<RssFeedDataModel> feedDataModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.list_item, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            ArrayList<?> entries = bundle.getParcelableArrayList("ArrayList");
            for (int i = 0; i < entries.size(); i++) {
                RssFeed entry = (RssFeed) entries.get(i);
                String strip = entry.getContent().replaceAll("<[^>]*>", "");
                //strip = strip.replaceAll("&.*?;", "");
                strip = strip.replaceAll("[ \\t\\r\\n]+"," ");
                entry.setContent(strip);
                //Log.d(TAG, "Link:" + entry.getLink());
                //Log.d(TAG, "Time: "+ entry.getPublished());
                feedDataModels.add(new RssFeedDataModel("placeholder", entry.getTitle(), entry.getContent(), entry.getLink(), "2017/01/27"));
            }
        }


        listView = getListView();
        ArrayAdapter<RssFeedDataModel> adapter = new CustomAdapter(getActivity(), 0, feedDataModels);
        listView.setAdapter(adapter);
        setListShown(true);
    }
}
