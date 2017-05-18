package com.example.sala01.application8;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.LongAdder;


public class LocationActivity extends AppCompatActivity implements LocationListener {

    public final int PERMISSION_LOCATION = 1;

    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        requestPermissions();

        latituteField = (TextView) findViewById(R.id.TextView02);
        longitudeField = (TextView) findViewById(R.id.TextView04);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private void loadLocation() {
        checkProviderIsEnabled();

        // Define the criteria how to select the locatioin provider -> use default
        provider = locationManager.getBestProvider(new Criteria(), true);

        if (provider != null) {
            Location location = locationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
                onLocationChanged(location);

                return;
            }
        }

        Toast.makeText(this, "Location fail", Toast.LENGTH_SHORT).show();
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION }, PERMISSION_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            int result = grantResults[i];

            if (result == PackageManager.PERMISSION_GRANTED) {
                String permission = permissions[i];

                switch (permission) {
                    case android.Manifest.permission.ACCESS_FINE_LOCATION:
                    case android.Manifest.permission.ACCESS_COARSE_LOCATION: {
                        loadLocation();
                    }
                }
            } else {
                Toast.makeText(this, "User reject permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkProviderIsEnabled() {
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        // locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_SHORT).show();
    }
}
