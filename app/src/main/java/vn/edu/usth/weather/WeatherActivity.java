package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {
    public static final String mess="Android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(mess,"onCreate");
//        ForecastFragment firstFragment = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container,firstFragment).commit();
//        WeatherFragment secondFragement =new WeatherFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container,secondFragement).commit();
        PagerAdapter adapter = new AdapterPage(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
//        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.music);
        mp.start();
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast toast = Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT);
                toast.show();
                super.onRestart();

            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        // Print Log
        Log.i(mess,"onStart");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        // Print Log
        Log.i(mess,"onStop");
    }

    /** Called just before the activity is destroyed. */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Print Log
        Log.i(mess,"onDestroy");
    }
    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        // Print Log
        Log.i(mess,"onPause");
    }
    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        // Print Log
        Log.i(mess,"onResume");      }


    /** Called just before the activity is restarted. */
    @Override
    protected void onRestart() {
        super.onRestart();
        // Print Log
        Log.i(mess,"onRestart");
    }
}