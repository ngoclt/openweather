package life.coder.openweather.api.model;

import java.util.List;

/**
 * Created by thegangocleylord on 11/11/17.
 */

public class OWForecast {
    private List<OWCityWeather> list;
    private OWCity city;

    public List<OWCityWeather> getList() {
        return list;
    }

    public OWCity getCity() {
        return city;
    }
}
