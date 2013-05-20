package com.mustafaiev.tair.dressin.dto;

import org.simpleframework.xml.Element;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/17/13
 * Time: 3:50 PM
 */
public class Area {

    @Element(name = "areaName")
    private String areaName;

    @Element(name = "country")
    private String country;

    @Element(name = "region")
    private String region;

    @Element(name = "latitude")
    private String latitude;

    @Element(name = "longitude")
    private String longitude;

    @Element(name = "population")
    private String population;

    @Element(name = "weatherUrl")
    private String weatherUrl;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getWeatherUrl() {
        return weatherUrl;
    }

    public void setWeatherUrl(String weatherUrl) {
        this.weatherUrl = weatherUrl;
    }
}
