package com.example.minifleettracker.ui;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.minifleettracker.R;
import com.example.minifleettracker.utils.NotificationHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions; 


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker vehicleMarker;
    private LatLng[] route;
    private int currentIndex = 0;

    private Handler handler = new Handler();
    private Runnable moveRunnable;

    private TextView speedText, engineText, doorText;

    private int speed = 0;
    private boolean engineOn = true;
    private boolean doorOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speedText = findViewById(R.id.speedText);
        engineText = findViewById(R.id.engineText);
        doorText = findViewById(R.id.doorText);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Simulasi rute
        route = new LatLng[] {
                new LatLng(-6.200000, 106.816666),
                new LatLng(-6.201000, 106.817000),
                new LatLng(-6.202000, 106.818000),
                new LatLng(-6.203000, 106.819000),
                new LatLng(-6.204000, 106.820000),
                new LatLng(-6.205000, 106.821000)
        };
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng start = route[0];
        vehicleMarker = mMap.addMarker(new MarkerOptions().position(start).title("Vehicle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 16f));

        startVehicleSimulation();
    }

    private void startVehicleSimulation() {
        moveRunnable = new Runnable() {
            @Override
            public void run() {
                currentIndex++;
                if (currentIndex >= route.length) {
                    currentIndex = 0;
                }

                LatLng newPos = route[currentIndex];
                vehicleMarker.setPosition(newPos);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(newPos));

                // Simulasi sensor
                speed = (int)(Math.random() * 120);
                boolean previousEngine = engineOn;
                engineOn = Math.random() > 0.3;
                doorOpen = Math.random() > 0.7;

                speedText.setText("Speed: " + speed + " km/h");
                engineText.setText("Engine: " + (engineOn ? "ON" : "OFF"));
                doorText.setText("Door: " + (doorOpen ? "Open" : "Closed"));

                // Notifikasi
                if (speed > 80) {
                    NotificationHelper.sendNotification(
                            MainActivity.this,
                            "Speed Alert 🚨",
                            "Speed exceeded: " + speed + " km/h",
                            1
                    );
                }

                if (speed > 0 && doorOpen) {
                    NotificationHelper.sendNotification(
                            MainActivity.this,
                            "Door Alert 🚪",
                            "Door is open while moving!",
                            2
                    );
                }

                if (engineOn != previousEngine) {
                    NotificationHelper.sendNotification(
                            MainActivity.this,
                            "Engine Status 🔧",
                            "Engine turned " + (engineOn ? "ON" : "OFF"),
                            3
                    );
                }

                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(moveRunnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(moveRunnable);
    }
}
