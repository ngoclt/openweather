package life.coder.openweather.api.model;

import java.util.List;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWForecast {
    private Double dt;
    private OWTemperature temp;
    private Float pressure;
    private Float humidity;
    private List<OWWeather> weather;

    public Double getDt() {
        return dt;
    }

    public OWTemperature getTemp() {
        return temp;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public List<OWWeather> getWeather() {
        return weather;
    }
}
