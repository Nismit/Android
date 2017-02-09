package ca.ciccc.simplerss.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ciccc.simplerss.RssFeedDataModel;
import ca.ciccc.simplerss.adapters.CustomAdapter;

public class FeedListView extends ListFragment {

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
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));

        listView = getListView();
        ArrayAdapter<RssFeedDataModel> adapter = new CustomAdapter(getActivity(), 0, feedDataModels);
        listView.setAdapter(adapter);
        setListShown(true);
    }
}
