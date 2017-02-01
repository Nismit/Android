package ca.ciccc.servicestesting;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        //return super.onStartCommand(intent, flags, startId);

        Notification.Builder notification = new Notification.Builder(this);
        notification.setContentTitle("Service");
        notification.setContentText("This is a body text");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setAutoCancel(true);
        notification.setDefaults(Notification.DEFAULT_ALL);


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


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Killed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
