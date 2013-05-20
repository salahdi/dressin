package com.mustafaiev.tair.dressin.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.mustafaiev.tair.dressin.constant.Constants;
import com.mustafaiev.tair.dressin.dto.WeatherData;
import com.mustafaiev.tair.dressin.manager.HttpManager;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.URL;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/17/13
 * Time: 2:19 PM
 */
public class WeatherDataAsyncLoader extends AsyncTaskLoader<WeatherData> {

    private WeatherData weatherData;

    private Double longtitude;

    private Double latitude;

    private static final String TAG = "com.mustafaiev.tair.dressin.loader.WeatherDataAsyncLoader";

    /**
     * @param context - {@link android.content.Context}
     */
    public WeatherDataAsyncLoader(final Context context, Double longtitude, Double latitude) {
        super(context);
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    @Override
    public WeatherData loadInBackground() {
        HttpManager httpManager = new HttpManager();
        try {
            final Header header = new BasicHeader("Content-Type", "application/xml");
            Uri url = getUri();
            HttpResponse r = httpManager.doGet(Uri.decode(url.toString()), header);
            final Serializer serial = new Persister();
            final InputStream inputStream = r.getEntity().getContent();
            WeatherData wd = serial.read(WeatherData.class, inputStream, false);
            InputStream image = (InputStream) new URL(wd.getCurrentCondition().getWeatherIconUrl()).getContent();
            wd.getCurrentCondition().setWeatherImage(Drawable.createFromStream(image, "weather_icon"));
            return wd;
        } catch (Exception e) {
            Log.e(TAG, "Weather data not fetched", e);
        }
        return null;
    }

    @Override
    public void deliverResult(final WeatherData data) {
        this.weatherData = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (this.weatherData != null) {
            this.deliverResult(this.weatherData);
        }
        if (takeContentChanged() || (this.weatherData == null)) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        this.onStopLoading();
    }

    private Uri getUri() {
        Uri.Builder ub = new Uri.Builder();
        ub.path(Constants.WWO_URL);
        ub.appendQueryParameter(Constants.WWO_KEY_PARAM, Constants.WWO_KEY);
        ub.appendQueryParameter(Constants.WWO_FORMAT, "xml");
        ub.appendQueryParameter(Constants.WWO_INCLUDELOCATION, "yes");
        ub.appendQueryParameter(Constants.WWO_NUM_OF_DAYS, "5");
        ub.appendQueryParameter(Constants.WWO_Q, getQueryValue());
        return ub.build();
    }

    private String getQueryValue() {
        if (this.longtitude != null && this.latitude != null) {
            return latitude + "," + longtitude;
        }
        return "";
    }
}
