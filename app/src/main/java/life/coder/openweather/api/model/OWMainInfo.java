package life.coder.openweather.api.model;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWMainInfo {
    private Double temp;
    private int humidity;
    private int pressure;
    private Float temp_min;
    private Float temp_max;

    public Double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public Float getTempMin() {
        return temp_min;
    }

    public Float getTempMax() {
        return temp_max;
    }
}
