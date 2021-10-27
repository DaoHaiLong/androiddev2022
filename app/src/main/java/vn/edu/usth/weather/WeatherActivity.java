package vn.edu.usth.weather;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    public static final String mess = "Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        Log.i(mess, "onCreate");
//        ForecastFragment firstFragment = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container,firstFragment).commit();
//        WeatherFragment secondFragement =new WeatherFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container,secondFragement).commit();
        PagerAdapter adapter = new AdapterPage(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
//        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.music);
        mp.start();
        RunImag();
        getAPIWeatherJson();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Refresh();
//                RunImag();
                break;

            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Print Log
        Log.i(mess, "onStart");
    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
        // Print Log
        Log.i(mess, "onStop");
    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Print Log
        Log.i(mess, "onDestroy");
    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Print Log
        Log.i(mess, "onPause");
    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Print Log
        Log.i(mess, "onResume");
    }


    /**
     * Called just before the activity is restarted.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        // Print Log
        Log.i(mess, "onRestart");
    }

    final Handler ProgressHandlerMessage = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {
            String content = msg.getData().getString("Server_Response");
            Toast toast = Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    @SuppressLint("StaticFieldLeak")
    public void Refresh() {
        // Practical work 13

//        final Handler handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//// This method is executed in main thread
//                String content = msg.getData().getString("Server_Response");
//                Toast toast = Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        };
//        Thread th = new Thread(() -> {
//// this method is run in a worker thread
//            try {
//// wait for 5 seconds to simulate a long network access
//                int i;
//                for (i=0;i<10;i++){
//                    Log.i(mess,"loop"+i);
//                    Thread.sleep(1000);
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//// Assume that we got our data from server
//            Bundle bundle = new Bundle();
//            bundle.putString("Server_Response", "Refreshing Start");
//// notify main thread
//            Message msg = new Message();
//            msg.setData(bundle);
//            handler.sendMessage(msg);
//        });
//        th.start();

        // Practical work 14
        AsyncTask<Integer, Integer, Integer> taskA = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPreExecute() {
//                String content = "Starting delay Android";
                Toast toast = Toast.makeText(getApplicationContext(), "Starting delay Android", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            protected void onPostExecute(Integer integer) {
//                String content = "Finished delay Android";
                Toast toast = Toast.makeText(getApplicationContext(), "Finished delay Android", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
//                String content = "Uppdate  Android";
                Toast toast = Toast.makeText(getApplicationContext(), "Uppdate  Android", +values[0]);
                toast.show();
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected Integer doInBackground(Integer... integers) {
                try {
// wait for 5 seconds to simulate a long network access
                    int i;
                    for (i = 0; i < 10; i++) {
//                    Log.i(mess,"loop"+i);
                        Thread.sleep(1000);
                        ProgressHandlerMessage.sendEmptyMessage(i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }

    public void RunImag() {
//        prac 15
//        AsyncTask<String, Integer, Bitmap> tsk = new AsyncTask<String, Integer, Bitmap>() {
//            Bitmap bitmap1;
//
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            protected Bitmap doInBackground(String... strings) {
//                try {
//                    Thread.sleep(1000);
//                    URL url = new URL(strings[0]);
//
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setDoInput(true);
//                    connection.connect();
//
//                    InputStream inputstream = connection.getInputStream();
//                    bitmap1 = BitmapFactory.decodeStream(inputstream);
//
//                    connection.disconnect();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return bitmap1;
//            }
//
//            @Override
//            protected void onPreExecute() {
//
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
////                        Toast.makeText(getApplicationContext(), "something beyond the sky", Toast.LENGTH_LONG).show();
//                ImageView logo = (ImageView) findViewById(R.id.logo);
//                logo.setImageBitmap(bitmap);
//            }
//        };
//        tsk.execute("https://usth.edu.vn/uploads/logo_moi-eng.png");

//        prac 16
        RequestQueue Requestqueue = Volley.newRequestQueue(this);
// a listener (kinda similar to onPostExecute())
        Response.Listener<Bitmap> listeners = new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView iv = (ImageView) findViewById(R.id.logo);
                        iv.setImageBitmap(response);
                    }
                };
// a simple request to the required image
        ImageRequest imageRequest = new ImageRequest(
                "https://usth.edu.vn/uploads/logo_moi-eng.png",
                listeners, 1, 0, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888,null);
// go!
        Requestqueue.add(imageRequest);
    }
//    Prac 17
    private void getAPIWeatherJson()
    {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=e3fc7cd1499bf87b25c7829f2ff41639";
        RequestQueue Requestqueue = Volley.newRequestQueue(this);
        JsonObjectRequest js = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView textview = (TextView) findViewById(R.id.city_name);
                        String out = "";
                        try {
                            String name = response.getString("name");
                            double temp = response.getJSONObject("main").getDouble("temp");

                            out = name + "\n" +  String.valueOf(temp) + "Long";

                        } catch (  JSONException e) {
                            e.printStackTrace();
                        }
                        textview.setText(out);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast toast= Toast.makeText(getApplicationContext(), "Fail to get data.....", Toast.LENGTH_SHORT);
               toast.show();
            }
        });
        Requestqueue.add(js);
   }
}
