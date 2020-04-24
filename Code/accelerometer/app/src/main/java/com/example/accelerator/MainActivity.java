package com.example.accelerator;

import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.lang.String;
import android.os.StrictMode;

public class MainActivity extends Activity implements SensorEventListener, LocationListener {

    protected LocationManager locationManager;
    //private TextView xText, yText, zText,Lat, Lon;
    private TextView Landing, Landing2, Result;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        // Create our Sensor Manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        //xText = (TextView) findViewById(R.id.xText);
        //yText = (TextView) findViewById(R.id.yText);
        //zText = (TextView) findViewById(R.id.zText);
        //Lat = (TextView) findViewById(R.id.Lat);
        //Lon = (TextView) findViewById(R.id.Lon);
        Landing = (TextView) findViewById(R.id.Landing);
        Landing.setText("Dublin Bike Mobile app");
        Landing2 = (TextView) findViewById(R.id.Landing2);
        Landing2.setText("Sharing your location to the server... ");
        Result = (TextView) findViewById(R.id.Result);
        Result.setText("");
        //variables.response_string = "Hello";
        /*
        ImageView Button = (ImageView)findViewById(R.id.button);
        Button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("http://google.com/");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        */
        Button mIdButtonHome = (Button)findViewById(R.id.idButtonHome);
        mIdButtonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://3.16.45.31/MapServer_initial.html"));
                startActivity(browserIntent);
            }
        });

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }

    int count = 0;

    String server_address = "http://3.16.45.31:5000/accel";
    post_http obj = new post_http();    @Override
    public void onSensorChanged(SensorEvent event) {
        //xText.setText("X: " + event.values[0]);
        //yText.setText("Y: " + event.values[1]);
        //zText.setText("Z: " + event.values[2]);
        count=count+1;
        if(count>50){
                String s1 = "{\"x\":";
                String s2 = ", \"y\":";
                String s3 = ", \"z\":";
                String accel_message = s1 +event.values[0] + s2 +event.values[1] +s3 +event.values[2] +"}";
                /*try {
                    //p1.http_post_message(server_address, accel_message);
                } catch (IOException e) {
                e.printStackTrace();
                }*/
                count = 0;
        }
    }
    public void onLocationChanged(Location location) {
            //Lat.setText("Lat: " + location.getLatitude());
            //Lon.setText("Lat: " + location.getLongitude());

            post_http obj = new post_http();
            try {
                String s1 = "{\"lat\":";
                String s2 = ", \"lon\":";
                String s3 = "}";
                String location_message = s1 + location.getLatitude() + s2 + location.getLongitude() + s3;
                //variables.response_string = obj.http_post_message(server_address, location_message);
                //Log.d("show", variables.response_string);
                String show_message= obj.http_post_message(server_address, location_message);
                Log.d("show", show_message);
                Landing2.setText(show_message);
                Result.setText("Click on the below button to get the location");
                /*
                final TextView myClickableUrl = (TextView) findViewById(R.id.TextView1);
                //myClickableUrl.setText("Click my web site: www.stackoverflow.com");
                //myClickableUrl.setText("Click my web site: www.google.com");
                myClickableUrl.setText("The route can be found at: http://3.16.45.31/MapServer_initial.html");
                Linkify.addLinks(myClickableUrl, Linkify.WEB_URLS);
                */

                Button mIdButtonHome = (Button)findViewById(R.id.idButtonHome);
                mIdButtonHome.setVisibility(View.VISIBLE);
                mIdButtonHome.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://3.16.45.31/MapServer_initial.html"));
                        startActivity(browserIntent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }
    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }
}