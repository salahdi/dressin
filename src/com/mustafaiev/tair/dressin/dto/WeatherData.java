package com.mustafaiev.tair.dressin.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 5:49 PM
 */
@Root(name = "data")
public class WeatherData {

    @Path(value = "request/type")
    @Element( data = false, required = false)
    private String type;

    @Path(value = "request/query")
    @Element( data = false, required = false)
    private String query;

    @Element(name = "nearest_area")
    private Area area;

    @Element(name = "current_condition")
    private CurrentCondition currentCondition;

    @ElementList(name = "weather", inline = true)
    private List<Forecast> forecastList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
