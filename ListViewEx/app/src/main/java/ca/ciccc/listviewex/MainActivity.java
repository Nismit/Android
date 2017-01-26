package ca.ciccc.listviewex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // <> Diamond brackets

    ListView listView;
    ArrayList<Person> persons;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);

        persons = new ArrayList<Person>();
        persons.add(new Person("Test", "Blue", 22));
        persons.add(new Person("Test", "Blue", 23));
        persons.add(new Person("Test", "Blue", 24));
        persons.add(new Person("Test", "Blue", 25));
        persons.add(new Person("Test", "Blue", 26));
        persons.add(new Person("Test", "Blue", 27));

        adapter = new MyAdapter(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                persons
                );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)view.findViewById(android.R.id.text1);
                Toast.makeText(getApplicationContext(),textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyAdapter extends ArrayAdapter<Person> {
        public MyAdapter(Context context, int resource, int textViewResourceId, List<Person> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            TextView nameTextView = (TextView)view.findViewById(android.R.id.text1);
            TextView ageAndColorTextView = (TextView)view.findViewById(android.R.id.text2);

            //System.out.println(persons.get(position).getName());

            nameTextView.setText(persons.get(position).getName());
            ageAndColorTextView.setText(persons.get(position).getColor()+", "+persons.get(position).getAge());

            return view;
        }
    }


}
