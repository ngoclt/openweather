package life.coder.openweather.api.model;

import java.util.Date;
import java.util.List;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWCityWeather {
    private long id;
    private long dt;
    private String name;
    private OWLocation coord;
    private List<OWWeather> weather;
    private OWMainInfo main;
    private OWWind wind;
    private int visibility;
    private OWSys sys;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OWLocation getCoord() {
        return coord;
    }

    public List<OWWeather> getWeather() {
        return weather;
    }

    public OWMainInfo getMain() {
        return main;
    }

    public OWWind getWind() {
        return wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public OWSys getSys() {
        return sys;
    }

    // Custom methods

    public Date getDate() {
        return new Date(dt);
    }
}
