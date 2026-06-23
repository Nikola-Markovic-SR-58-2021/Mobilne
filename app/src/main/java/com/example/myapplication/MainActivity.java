package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView3;
    ImageButton imageButton;
    ImageView imageView;
    Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView3 = findViewById(R.id.textView3);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        LocationListener listener = location -> {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            textView3.setText("Lat: " + String.format("%.2f", latitude) + ", Lng: " + String.format("%.2f", longitude));
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

            Location lastKnown = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (lastKnown != null) {
                textView3.setText("Lat: " + String.format("%.2f", lastKnown.getLatitude()) +
                        ", Lng: " + String.format("%.2f", lastKnown.getLongitude()));
            } else {
                textView3.setText("Waiting for location...");
            }

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 1);
        }

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setText("button clicked");
            }
        });

        imageView = findViewById(R.id.imageView);

        mySwitch = findViewById(R.id.mySwitch);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView3.setText("Switch is On");
                } else {
                    textView3.setText("Switch is Off");
                }
            }
        });

    }  // <-- this was missing, closes onCreate

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

