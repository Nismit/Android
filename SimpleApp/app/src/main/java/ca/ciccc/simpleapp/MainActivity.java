package ca.ciccc.simpleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSecond(View v) {
        // Code for starting second activity
        System.out.println("Button clicked.");
        Intent myIntention = new Intent(this, SecondActivity.class);
        startActivity(myIntention);
    }
}
