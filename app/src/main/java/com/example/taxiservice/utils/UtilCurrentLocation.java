package com.example.taxiservice.utils;



import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class UtilCurrentLocation {

    private final Activity activity;
    private final LocationManager locationManager;
    private LocationListener locationListener;
    private Location currentLocation;

    public UtilCurrentLocation(Activity activity) {
        this.activity = activity;
        this.locationManager = (LocationManager) activity.getSystemService(Activity.LOCATION_SERVICE);
    }

    public void getCurrentLocation(LocationCallback callback) {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                currentLocation = location;
                callback.onLocationFound(location);
                stopLocationUpdates();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(@NonNull String provider) {}

            @Override
            public void onProviderDisabled(@NonNull String provider) {}
        };

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private void stopLocationUpdates() {
        if (locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public interface LocationCallback {
        void onLocationFound(Location location);
    }
}


//activity code

//package com.example.taxiservice;
//
//import android.location.Location;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import com.example.taxiservice.utils.UtilCurrentLocation;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//    private UtilCurrentLocation utilCurrentLocation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(this);
//        }
//
//        utilCurrentLocation = new UtilCurrentLocation(this);
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mMap = googleMap;
//        fetchCurrentLocation();
//    }
//
//    private void fetchCurrentLocation() {
//        utilCurrentLocation.getCurrentLocation(new UtilCurrentLocation.LocationCallback() {
//            @Override
//            public void onLocationFound(Location location) {
//                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 100) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                fetchCurrentLocation();
//            }
//        }
//    }
//}
//manifest

//dependecy gradle

//implementation 'com.google.android.gms:play-services-maps:18.1.0'


//<uses-permission android:name="android.permission.INTERNET"/>
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
//<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
//
//<application>
//    ...
//    <meta-data
//android:name="com.google.android.geo.API_KEY"
//android:value="YOUR_API_KEY_HERE"/>
//</application>



//   <meta-data
//android:name="com.google.android.geo.API_KEY"
//android:value="YOUR_API_KEY_HERE"/>

//uiiuiu
//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//android:layout_width="match_parent"
//android:layout_height="match_parent">
//
//    <fragment
//android:id="@+id/map"
//android:name="com.google.android.gms.maps.SupportMapFragment"
//android:layout_width="match_parent"
//android:layout_height="match_parent"/>
//</RelativeLayout>


