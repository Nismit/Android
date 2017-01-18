package ca.ciccc.savedata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String SHARED_PREF = "ca.ciccc.savedata.SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // checking preferences file at first
        preferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        boolean isDarkTheme = preferences.getBoolean("dark_theme", false);
        if(isDarkTheme) {
            setTheme(R.style.AppThemeDark);
        }else {
            setTheme(R.style.AppTheme);
        }

        // making a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                //Toast.makeText(this,"Settings working", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.Menu2:
                Toast.makeText(this,"Menu 2", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
