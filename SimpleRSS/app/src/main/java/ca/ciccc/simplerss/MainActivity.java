package ca.ciccc.simplerss;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variables
    ListView listView;

    // Custom ArrayList
    ArrayList<RssFeedDataModel> feedDataModels = new ArrayList<>();
    //View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius. Phasellus cursus mattis elit, et accumsan metus tempor eget.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));
        feedDataModels.add(new RssFeedDataModel("placeholder", "Title Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis odio ut metus auctor varius.", "www.google.com", "2017/01/27"));



//        adapter = new mAdapter(
//                this,
//                android.R.layout.simple_list_item_2,
//                android.R.id.text1,
//                feedDataModels
//        );

        listView = (ListView)findViewById(R.id.rssView);
        ArrayAdapter<RssFeedDataModel> adapter = new customAdapter(this, 0, feedDataModels);
        listView.setAdapter(adapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView textView = (TextView)view.findViewById(android.R.id.text1);
//                Toast.makeText(getApplicationContext(),textView.getText(),Toast.LENGTH_SHORT).show();
//            }
//        });
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
            //View view = super.getView(position, convertView, parent);
            // adapter.notifyDataChanged()

            RssFeedDataModel dataProperty = feedDataModelList.get(position);

            System.out.println("Working");
            System.out.println(dataProperty.getTitle());

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