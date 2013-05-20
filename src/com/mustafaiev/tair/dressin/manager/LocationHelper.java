package com.mustafaiev.tair.dressin.manager;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/17/13
 * Time: 11:20 PM
 * <p/>
 * Inspired by http://stackoverflow.com/questions/3145089/what-is-the-simplest-and-most-robust-way-to-get-the-users-current-location-in-a
 */
public class LocationHelper {

    private Timer timer;
    private LocationManager lm;
    private LocationResult locationResult;
    private boolean isGpsEnabled = false;
    private boolean isNetworkEnabled = false;

    public boolean getLocation(Context context, LocationResult result) {
        Log.d(LocationHelper.class.getCanonicalName(), "getLocation() started");
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult = result;
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try {
            isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        //don't start listeners if no provider is enabled
        if (!isGpsEnabled && !isNetworkEnabled)
            return false;

        if (isNetworkEnabled) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 100, locationListenerNetwork);
        } else if (isGpsEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 100, locationListenerGps);
        }

        timer = new Timer();
        timer.schedule(new GetLastLocation(), 10000);
        Log.d(LocationHelper.class.getCanonicalName(), "getLocation() finished");
        return true;
    }

    LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.d(LocationListener.class.getCanonicalName(), "GPS onLocationChanged() called, location retrieved");
            timer.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerNetwork);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.d(LocationListener.class.getCanonicalName(), "Network onLocationChanged() called, location retrieved");
            timer.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerGps);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private class GetLastLocation extends TimerTask {
        @Override
        public void run() {
            Log.d(GetLastLocation.class.getCanonicalName(), "Getting location from Known Location");
            lm.removeUpdates(locationListenerGps);
            lm.removeUpdates(locationListenerNetwork);

            Location networkLocation = null, gpsLocation = null;
            if (isGpsEnabled)
                gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (isNetworkEnabled)
                networkLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            //if there are both values use the latest one
            if (gpsLocation != null && networkLocation != null) {
                if (gpsLocation.getTime() > networkLocation.getTime())
                    locationResult.gotLocation(gpsLocation);
                else
                    locationResult.gotLocation(networkLocation);
                return;
            }

            if (gpsLocation != null) {
                locationResult.gotLocation(gpsLocation);
                return;
            }
            if (networkLocation != null) {
                locationResult.gotLocation(networkLocation);
                return;
            }
            locationResult.gotLocation(null);
        }
    }

    public static abstract class LocationResult {
        public abstract void gotLocation(Location location);
    }

}
