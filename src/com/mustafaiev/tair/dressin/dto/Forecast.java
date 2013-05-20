package com.mustafaiev.tair.dressin.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 6:13 PM
 */
@Root(name = "weather")
public class Forecast {

    @Element(name = "date")
    private String date;

    @Element(name = "tempMaxC")
    private String temperatureCelsiumMax;

    @Element(name = "tempMinC")
    private String temperatureCelsiumMin;

    @Element(name = "tempMaxF")
    private String temperatureFarenheitMax;

    @Element(name = "tempMinF")
    private String temperatureFarenheitMin;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperatureCelsiumMax() {
        return temperatureCelsiumMax;
    }

    public void setTemperatureCelsiumMax(String temperatureCelsiumMax) {
        this.temperatureCelsiumMax = temperatureCelsiumMax;
    }

    public String getTemperatureCelsiumMin() {
        return temperatureCelsiumMin;
    }

    public void setTemperatureCelsiumMin(String temperatureCelsiumMin) {
        this.temperatureCelsiumMin = temperatureCelsiumMin;
    }

    public String getTemperatureFarenheitMax() {
        return temperatureFarenheitMax;
    }

    public void setTemperatureFarenheitMax(String temperatureFarenheitMax) {
        this.temperatureFarenheitMax = temperatureFarenheitMax;
    }

    public String getTemperatureFarenheitMin() {
        return temperatureFarenheitMin;
    }

    public void setTemperatureFarenheitMin(String temperatureFarenheitMin) {
        this.temperatureFarenheitMin = temperatureFarenheitMin;
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
}
