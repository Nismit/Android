package ca.ciccc.quiz2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // For debug
    public static final String TAG = "quiz-main";
    // Action name
    public static final String NAME_ACTION = "cloud.main.activity";

    BroadcastReceiver mReceiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Init view");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Received! Open the second activity", Toast.LENGTH_SHORT).show();
                openSecondActivity();
            }
        };

        filter = new IntentFilter();
        filter.addAction(NAME_ACTION);
        registerReceiver(mReceiver, filter);
        Log.d(TAG, "Receiver registered");
    }

    public void sendBroadcast(View v) {
        sendBroadcast(new Intent().setAction(NAME_ACTION));
        Log.d(TAG, "Send Broadcast");
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        Log.d(TAG, "Open SecondActivity");
    }

    public void getNotification(View v) {
        Log.d(TAG, "Init Notification");
        Notification.Builder notification = new Notification.Builder(this);
        notification.setContentTitle("INFO");
        notification.setContentText("Check my information :)");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setAutoCancel(true);
        notification.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ThirdActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);


        // This is a good way to support lower api level android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //notification.setTicker("test");
            notification.setPriority(Notification.PRIORITY_HIGH);
            notificationManager.notify(0, notification.build());
            Log.d(TAG, "Built Notification");
        }else {
            notificationManager.notify(0, notification.getNotification());
        }
    }

    public void openThirdActivity(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
        Log.d(TAG, "Open ThirdActivity");
    }
}
