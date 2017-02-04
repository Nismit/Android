package ca.ciccc.quiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    // For debug
    public static final String TAG = "quiz-second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "Init view");
    }

    public void backToHome(View v) {
        Log.d(TAG, "Back to Home");
        finish();
    }

    public void openCredit(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
        Log.d(TAG, "Open Credit");
    }

}
