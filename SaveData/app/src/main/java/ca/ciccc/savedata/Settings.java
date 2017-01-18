package ca.ciccc.savedata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Settings extends AppCompatActivity {

    SharedPreferences preferences; //read
    SharedPreferences.Editor editor; //write
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferences = getSharedPreferences(MainActivity.SHARED_PREF, MODE_PRIVATE);
        check = (CheckBox) findViewById(R.id.checkBox2);
        check.setChecked(preferences.getBoolean("dark_theme", false));
        editor = preferences.edit();
    }

    public void backToMain(View v) {
        //CheckBox check = (CheckBox) findViewById(R.id.checkBox2);
        boolean isDarkTheme = check.isChecked();
        editor.putBoolean("dark_theme", isDarkTheme);
        editor.commit();
        finish();
    }
}
