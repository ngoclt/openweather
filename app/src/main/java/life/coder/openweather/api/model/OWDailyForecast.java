package life.coder.openweather.api.model;

import java.util.List;

public class OWDailyForecast {
    private OWCity city;
    private List<OWDailyWeather> list;

    public OWCity getCity() {
        return city;
    }

    public List<OWDailyWeather> getList() {
        return list;
    }
}
