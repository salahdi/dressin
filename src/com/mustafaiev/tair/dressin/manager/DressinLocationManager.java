package com.mustafaiev.tair.dressin.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.*;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/17/13
 * Time: 5:47 PM
 */
public class DressinLocationManager {

    private final Context context;

    private Double lontitude;

    private Double latitude;

    public DressinLocationManager(Context cntxt) {
        Log.d(DressinLocationManager.class.getCanonicalName(), "Location receiver initialization started");
        this.context = cntxt;
        LocationHelper myLocation = new LocationHelper();
        myLocation.getLocation(this.context, locationResult);
    }

    private LocationHelper.LocationResult locationResult = new LocationHelper.LocationResult() {
        @Override
        public void gotLocation(Location location) {
            Log.d(DressinLocationManager.class.getCanonicalName(), "Location received, location is: " + location.getLatitude() + " - "+ location.getLongitude());
            DressinLocationManager.this.latitude = location.getLatitude();
            DressinLocationManager.this.lontitude = location.getLongitude();
        }
    };

    public Double getLontitude() {
        return lontitude;
    }

    public Double getLatitude() {
        return latitude;
    }

}
