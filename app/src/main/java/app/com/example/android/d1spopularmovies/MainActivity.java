package app.com.example.android.d1spopularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));



           /*getFragmentManager().beginTransaction()
                   .replace(android.R.id.content, new SettingsFragment())
                   .commit();


            */
           SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                        String sort = prefs.getString(getString(R.string.pref_sort_key),
                    getString(R.string.pref_sort_default));
            //new MainActivityFragment().update(sort);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
