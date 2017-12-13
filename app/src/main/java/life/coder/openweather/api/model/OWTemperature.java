package life.coder.openweather.api.model;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWTemperature {
    private Float day;
    private Float min;
    private Float max;
    private Float night;
    private Float eve;
    private Float morn;

    public Float getDay() {
        return day;
    }

    public Float getMin() {
        return min;
    }

    public Float getMax() {
        return max;
    }

    public Float getNight() {
        return night;
    }

    public Float getEve() {
        return eve;
    }

    public Float getMorn() {
        return morn;
    }
}
