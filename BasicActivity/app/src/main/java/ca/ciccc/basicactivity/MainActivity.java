package ca.ciccc.basicactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextView;
    TextView resultView;

    static final int REVERSE_REQUEST = 1;
    static final int PREFIX_REQUEST = 2;
    static final int SUFFIX_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextView = (EditText) findViewById(R.id.editTextView);
        resultView = (TextView) findViewById(R.id.resultView);
    }

    public void reverseButton(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", editTextView.getText().toString());
        intent.putExtra("requestCode", REVERSE_REQUEST);
        startActivityForResult(intent, REVERSE_REQUEST);
    }

    public void prefixButton(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", editTextView.getText().toString());
        intent.putExtra("requestCode", PREFIX_REQUEST);
        startActivityForResult(intent, PREFIX_REQUEST);
    }

    public void suffixButton(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", editTextView.getText().toString());
        intent.putExtra("requestCode", SUFFIX_REQUEST);
        startActivityForResult(intent, SUFFIX_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REVERSE_REQUEST) {
                String t = data.getStringExtra("name");
                resultView.setText(t);
            }else if(requestCode == PREFIX_REQUEST) {
                String t = data.getStringExtra("name");
                resultView.setText(t);
            }else if(requestCode == SUFFIX_REQUEST) {
                String t = data.getStringExtra("name");
                resultView.setText(t);
            }
        }
    }
}
