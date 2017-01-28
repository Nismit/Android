package ca.ciccc.quiz1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Cat> cats;
    mAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        cats = new ArrayList<Cat>();

        cats.add(new Cat("Persian", "White", 5));
        cats.add(new Cat("Breed", "Black", 6));
        cats.add(new Cat("Persian", "Brown", 7));
        cats.add(new Cat("Breed", "Gray", 8));
        cats.add(new Cat("Persian", "White", 5));
        cats.add(new Cat("Breed", "Black", 6));
        cats.add(new Cat("Persian", "Brown", 7));
        cats.add(new Cat("Breed", "Gray", 8));
        cats.add(new Cat("Persian", "White", 5));
        cats.add(new Cat("Breed", "Black", 6));
        cats.add(new Cat("Persian", "Brown", 7));
        cats.add(new Cat("Breed", "Gray", 8));

        adapter = new mAdapter(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                cats
        );

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Delete The Data");
                dialog.setMessage(cats.get(position).getBreed() +", "+cats.get(position).getColor()+", "+cats.get(position).getAge());
                //dialog.setMessage("test");


                // CANCEL BUTTON ACTION
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Delete Canceled",Toast.LENGTH_SHORT).show();
                    }
                });

                // DELETE BUTTON ACTION
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cats.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Delete Confirmed",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.create();
                dialog.show();
                return true;
            }
        });

    }

    public class mAdapter extends ArrayAdapter<Cat> {
        public mAdapter(Context context, int resource, int textViewResourceId, List<Cat> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            TextView nameTextView = (TextView)view.findViewById(android.R.id.text1);
            TextView ageAndColorTextView = (TextView)view.findViewById(android.R.id.text2);

            nameTextView.setText(cats.get(position).getBreed());
            ageAndColorTextView.setText(cats.get(position).getColor()+", "+cats.get(position).getAge());

            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_data:



                final View cDialogView = getLayoutInflater().inflate(R.layout.dialog_main, null);
                Button cancelButton = (Button)cDialogView.findViewById(R.id.cancelBtn);
                Button addButton = (Button) cDialogView.findViewById(R.id.addBtn);
                final EditText breedText = (EditText) cDialogView.findViewById(R.id.breedText);
                final EditText colorText = (EditText) cDialogView.findViewById(R.id.colorText);
                final EditText ageText = (EditText) cDialogView.findViewById(R.id.ageText);

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Delete The Data");
                dialog.setView(cDialogView);
                // dialog.show() returns dialog
                final AlertDialog dialog2 = dialog.show();

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //System.out.println("WORK");
                        if(!breedText.getText().toString().equals("") && !colorText.getText().toString().equals("") && !ageText.getText().toString().equals("")) {
                            cats.add(new Cat(breedText.getText().toString(), colorText.getText().toString(), Integer.parseInt(ageText.getText().toString())));
                            adapter.notifyDataSetChanged();
                            dialog2.dismiss();
                            Toast.makeText(getApplicationContext(),"Added Data!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Not Vailed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //System.out.println("CANCEL");
                        Toast.makeText(getApplicationContext(),"Adding Canceled",Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }
                });

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
}
