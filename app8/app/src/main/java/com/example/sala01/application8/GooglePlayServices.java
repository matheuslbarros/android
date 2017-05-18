package com.example.sala01.application8;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class GooglePlayServices extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;

    private Location lastLocation;

    private TextView latitudeText, longitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_play_services);

        // Create an instance of GoogleAPIClient.
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        latitudeText = (TextView) findViewById(R.id.TextView02);
        longitudeText = (TextView) findViewById(R.id.TextView04);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("A", "onConnected");

        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (lastLocation != null) {
            latitudeText.setText(String.valueOf(lastLocation.getLatitude()));
            longitudeText.setText(String.valueOf(lastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        Log.i("A", "onStart");
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }
}
