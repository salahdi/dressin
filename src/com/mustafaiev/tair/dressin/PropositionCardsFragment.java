package com.mustafaiev.tair.dressin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockListFragment;
import com.mustafaiev.tair.dressin.dto.WeatherData;
import com.mustafaiev.tair.dressin.loader.WeatherDataAsyncLoader;
import com.mustafaiev.tair.dressin.manager.DressinLocationManager;
import com.mustafaiev.tair.dressin.manager.LocationHelper;
import com.mustafaiev.tair.dressin.utils.Utils;
import com.mustafaiev.tair.dressin.widget.propositioncardslist.PropositionCardsListViewAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 5:13 PM
 */
public class PropositionCardsFragment extends SherlockListFragment implements LoaderManager.LoaderCallbacks<WeatherData> {

    private Double longtitude;

    private Double latitude;

    private boolean isWeatherFetched = false;

    private ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LocationHelper myLocation = new LocationHelper();
        myLocation.getLocation(getSherlockActivity(), locationResult);
        return inflater.inflate(R.layout.proposition_cards_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (dialog == null) {
            dialog = Utils.initProgressDialog(getSherlockActivity());
            dialog.show();
        }
        this.getView().findViewById(R.id.weather_container).setVisibility(View.INVISIBLE);
    }

    @Override
    public Loader<WeatherData> onCreateLoader(int i, Bundle bundle) {
        return new WeatherDataAsyncLoader(this.getSherlockActivity(), longtitude, latitude);
    }

    @Override
    public void onLoadFinished(Loader<WeatherData> weatherDataLoader, WeatherData weatherData) {
        if (weatherData != null) {
            isWeatherFetched = true;
            Activity activity = this.getSherlockActivity();
            TextView city = (TextView) activity.findViewById(R.id.weather_city);
            city.setText(weatherData.getArea().getAreaName());

            TextView date = (TextView) activity.findViewById(R.id.weather_date);
            date.setText(weatherData.getCurrentCondition().getObservationTime());

            TextView desc = (TextView) activity.findViewById(R.id.weather_desc);
            desc.setText(weatherData.getCurrentCondition().getWeatherDesc());

            TextView temp = (TextView) activity.findViewById(R.id.weather_temperature);
            temp.setText(weatherData.getCurrentCondition().getTemperatureCelsium());

            ImageView wi = (ImageView) activity.findViewById(R.id.weather_image);
            wi.setImageDrawable(weatherData.getCurrentCondition().getWeatherImage());

            setListAdapter(new PropositionCardsListViewAdapter());
            if (dialog != null) {
                dialog.dismiss();
            }
            this.getView().findViewById(R.id.weather_container).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<WeatherData> weatherDataLoader) {
    }


    private LocationHelper.LocationResult locationResult = new LocationHelper.LocationResult() {
        @Override
        public void gotLocation(Location location) {
            latitude = location.getLatitude();
            longtitude = location.getLongitude();
            Log.d(LocationHelper.LocationResult.class.getCanonicalName(), "Location received, location is: " + latitude + " - " + longtitude);
            if (!isWeatherFetched) {
                getLoaderManager().initLoader(11, null, PropositionCardsFragment.this);
            }
        }
    };

}
