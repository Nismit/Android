package ca.ciccc.servicestesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "trackFunc";
    ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "inital");
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    startTestService();
                    Log.d(TAG, "Start Service");
                }else {
                    stopTestService();
                }
            }
        });

    }

    public void startTestService() {
        //startService(new Intent(this, MyService.class));
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction("testing");
        intent.putExtra("action", "testing");
        startService(intent);
    }

    public void stopTestService() {
        stopService(new Intent(this, MyService.class));
    }
}
