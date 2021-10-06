package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {
    public static final String mess="Android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(mess,"onCreate");
        ForecastFragment firstFragment = new ForecastFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,firstFragment).commit();
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