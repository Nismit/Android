package ca.ciccc.servicestesting;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("Something");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("test");
//        if(intent.getStringExtra("action").equals("testing")) {
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//        }
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
    }
}
