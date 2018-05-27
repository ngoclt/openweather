package life.coder.openweather.api.model;

import java.util.Date;
import java.util.List;

public class OWDailyWeather {
    private long dt;
    private OWDailyTemperature temp;
    private List<OWWeather> weather;
    private Float speed;
    private Float deg;
    private Float clouds;
    private Float rain;
    private Float humidity;
    private Float pressure;

    public long getDt() {
        return dt;
    }

    public OWDailyTemperature getTemp() {
        return temp;
    }

    public List<OWWeather> getWeather() {
        return weather;
    }

    public Float getSpeed() {
        return speed;
    }

    public Float getDeg() {
        return deg;
    }

    public Float getClouds() {
        return clouds;
    }

    public Float getRain() {
        return rain;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getPressure() {
        return pressure;
    }

    // Custom methods

    public Date getDate() {
        return new Date(dt * 1000);
    }
}
