package life.coder.openweather.api.model;

/**
 * Created by ngocle on 11/14/17.
 */

public class OWCityWeather {

    private OWCity weather;

    public OWCityWeather() {
    }

    public void setWeather(OWCity weather) {
        this.weather = weather;
    }

    public OWCity getWeather() {
        return weather;
    }
}
