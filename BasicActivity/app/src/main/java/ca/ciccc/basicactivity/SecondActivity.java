package ca.ciccc.basicactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        if(intent.hasExtra("requestCode") && intent.hasExtra("name")) {
            int requestCode = intent.getExtras().getInt("requestCode");
            String name = intent.getStringExtra("name");
            if(requestCode == 1) {
                name = new StringBuilder(name).reverse().toString();
            }else if(requestCode == 2) {
                name = "PREFIX-" + name;
            }else if(requestCode == 3) {
                name = name + "-SUFFIX";
            }else {
                // Err
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name", name);
            setResult(RESULT_OK, returnIntent);
        }else {
            System.out.println("The data is null.");
            Intent returnIntent = new Intent();
            returnIntent.putExtra("error", "There is no data");
            setResult(RESULT_CANCELED, returnIntent);
        }

    }

    public void executeFunc(View v) {
        finish();
    }
}
