package life.coder.openweather.api.model;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWMainInfo {
    private Float temp;
    private Float humidity;
    private Float pressure;
    private Float temp_min;
    private Float temp_max;

    public Float getTemp() {
        return temp;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getTempMin() {
        return temp_min;
    }

    public Float getTempMax() {
        return temp_max;
    }
}
