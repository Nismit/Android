package ca.ciccc.simplerss.fragments;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.ciccc.simplerss.R;
import ca.ciccc.simplerss.RssFeedDataModel;

public class feedListView extends ListFragment {

    // Variables
    ListView listView;

    // Custom ArrayList
    ArrayList<RssFeedDataModel> feedDataModels = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate()
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class customAdapter extends ArrayAdapter<RssFeedDataModel> {

        private Context context;
        private List<RssFeedDataModel> feedDataModelList;

        public customAdapter(Context context, int resource, List<RssFeedDataModel> objects) {
            super(context, resource, objects);

            this.context = context;
            this.feedDataModelList = objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RssFeedDataModel dataProperty = feedDataModelList.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View view;

            view = inflater.inflate(R.layout.list_item, null);

            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView description = (TextView) view.findViewById(R.id.description);
            TextView url = (TextView) view.findViewById(R.id.siteURL);
            TextView date = (TextView) view.findViewById(R.id.sitePublishDate);

            title.setText(dataProperty.getTitle());
            url.setText(dataProperty.getUrl());
            date.setText(dataProperty.getDate());

            int descriptionLength = dataProperty.getDescription().length();
            if(descriptionLength >= 85){
                String descriptionTrim = dataProperty.getDescription().substring(0, 85) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(dataProperty.getDescription());
            }

            int imageID = context.getResources().getIdentifier(dataProperty.getThumbnail(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }


}
