package com.mustafaiev.tair.dressin.dto;

import android.graphics.drawable.Drawable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.InputStream;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 6:03 PM
 */

public class CurrentCondition {

    @Element(name = "observation_time")
    private String observationTime;

    @Element(name = "temp_C")
    private String temperatureCelsium;

    @Element(name = "temp_F")
    private String temperatureFarenheit;

    @Element(name = "weatherCode")
    private String weatherCode;

    @Element(name = "weatherIconUrl")
    private String weatherIconUrl;

    @Element(name = "weatherDesc")
    private String weatherDesc;

    @Element(name = "windspeedMiles")
    private String windSpeedMiles;

    @Element(name = "windspeedKmph")
    private String windSpeedKmph;

    @Element(name = "winddirDegree")
    private String windDirDegree;

    @Element(name = "winddir16Point")
    private String windDirSexteenPoint;

    @Element(name = "precipMM")
    private String precipMM;

    @Element(name = "humidity")
    private String humidity;

    @Element(name = "visibility")
    private String visibility;

    @Element(name = "pressure")
    private String pressure;

    @Element(name = "cloudcover")
    private String cloudCover;

    private Drawable weatherImage;

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getTemperatureCelsium() {
        return temperatureCelsium;
    }

    public void setTemperatureCelsium(String temperatureCelsium) {
        this.temperatureCelsium = temperatureCelsium;
    }

    public String getTemperatureFarenheit() {
        return temperatureFarenheit;
    }

    public void setTemperatureFarenheit(String temperatureFarenheit) {
        this.temperatureFarenheit = temperatureFarenheit;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWindSpeedMiles() {
        return windSpeedMiles;
    }

    public void setWindSpeedMiles(String windSpeedMiles) {
        this.windSpeedMiles = windSpeedMiles;
    }

    public String getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public void setWindSpeedKmph(String windSpeedKmph) {
        this.windSpeedKmph = windSpeedKmph;
    }

    public String getWindDirDegree() {
        return windDirDegree;
    }

    public void setWindDirDegree(String windDirDegree) {
        this.windDirDegree = windDirDegree;
    }

    public String getWindDirSexteenPoint() {
        return windDirSexteenPoint;
    }

    public void setWindDirSexteenPoint(String windDirSexteenPoint) {
        this.windDirSexteenPoint = windDirSexteenPoint;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(String precipMM) {
        this.precipMM = precipMM;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Drawable getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(Drawable weatherImage) {
        this.weatherImage = weatherImage;
    }
}
