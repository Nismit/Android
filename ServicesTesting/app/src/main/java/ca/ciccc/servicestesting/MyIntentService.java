package ca.ciccc.servicestesting;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static final String MITCH = "mitch-myintent";

    public MyIntentService() {
        super("Something");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //System.out.println("test");
//        if(intent.getStringExtra("action").equals("testing")) {
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//        }
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
        //Log.d(MITCH, "INTENT NO YATU");

        Notification.Builder notification = new Notification.Builder(this);
        notification.setContentTitle("Intent Service");
        notification.setContentText("This is a body text");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setAutoCancel(true);
        //notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);


        // This is a good way to support lower api level android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //notification.setFullScreenIntent(pendingIntent, true);
            notification.setPriority(Notification.PRIORITY_HIGH);
            //notification.setTicker("test");
            notificationManager.notify(0, notification.build());
        }else {
            notificationManager.notify(0, notification.getNotification());
        }
    }
}
