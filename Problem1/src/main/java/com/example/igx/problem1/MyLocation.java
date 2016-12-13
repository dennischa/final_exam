package com.example.igx.problem1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created by user on 2016-12-13.
 */

public class MyLocation extends Activity implements LocationListener {

    protected LocationManager locationManager;
    Location location;
    double lat;
    double lon;

    private final Context mContext;

     public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras){}


    public MyLocation(Context context) {
        this.mContext = context;
        GetLocation();
    }

    public String GetLocation() {

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
                 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1000 * 60 * 1, this);
                 location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                lat = location.getLatitude();
                lon = location.getLongitude();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String tmp = Double.toString(location.getLatitude()) + "    "+ Double.toString(location.getLongitude());
        return tmp;
    }
}