package ca.ciccc.simplerss.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ca.ciccc.simplerss.R;
import ca.ciccc.simplerss.RssFeedDataModel;

public class CustomAdapter extends ArrayAdapter<RssFeedDataModel> {
    private int mResource;
    private Context context;
    private List<RssFeedDataModel> feedDataModelList;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, int resource, List<RssFeedDataModel> objects) {
        super(context, resource, objects);

        mResource = resource;
        this.context = context;
        feedDataModelList = objects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssFeedDataModel dataProperty = feedDataModelList.get(position);

        //LayoutInflater inflater =

        View view;

        if(convertView == null) {
            view = mInflater.inflate(R.layout.list_item, parent, false);
        }else {
            view = convertView;
        }

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
