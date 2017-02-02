package ca.ciccc.testingmultithread;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final long MYPASSWORD = 137900;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
    }

    public void performGuessing(View v) {
        final PasswordGuesser passwordGuesser = new PasswordGuesser();
        passwordGuesser.execute(1000l,MYPASSWORD);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                passwordGuesser.cancel(true);
            }
        };

        //Handler handler = new Handler();
        Handler handler = new Handler();
        boolean isDelayed = handler.postDelayed(runnable, 3000);
        if(isDelayed) {
            handler.removeCallbacks(runnable);
        }

    }

    class PasswordGuesser extends AsyncTask<Long, String, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Long doInBackground(Long... params) {
            long rate = params[0];
            long range = 1000000;
            long count = 0;
            long guess = 0;
            Random rand = new Random();

            while (guess != MYPASSWORD) {
                guess = Math.abs(rand.nextLong()) % range;
                count++;
                if(count % rate == 0) {
                    publishProgress("Guess Number: " +count +" Last guess: " + guess);
                    count++;
                }
            }
            return guess;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            textView.setText("Found Password: " + aLong);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
