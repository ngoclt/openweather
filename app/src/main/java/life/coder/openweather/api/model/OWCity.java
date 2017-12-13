package life.coder.openweather.api.model;

import java.util.List;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWCity {
    private int id;
    private String name;
    private OWLocation coord;
    private String country;
    private List<OWWeather> weather;
    private OWMainInfo main;
    private int visibility;
    private OWSys sys;

    public OWMainInfo getMain() {
        return main;
    }

    public OWSys getSys() {
        return sys;
    }

    public List<OWWeather> getWeather() {
        return weather;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OWLocation getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
