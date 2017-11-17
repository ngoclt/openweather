package life.coder.openweather.repository;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import life.coder.openweather.api.WeatherService;
import life.coder.openweather.db.WeatherDatabase;
import life.coder.openweather.repository.dao.DayWeatherDAO;
import life.coder.openweather.repository.entity.DayWeather;
import life.coder.openweather.util.AppExecutors;

/**
 * Created by thegaylord on 07/11/2017.
 */

@Singleton
public class DayWeatherRepository {

    private final WeatherDatabase db;

    private final DayWeatherDAO dayWeatherDAO;

    private final WeatherService weatherService;

    private final AppExecutors appExecutors;

    @Inject
    public DayWeatherRepository(WeatherDatabase db, DayWeatherDAO dayWeatherDAO, WeatherService weatherService, AppExecutors appExecutors) {
        this.db = db;
        this.dayWeatherDAO = dayWeatherDAO;
        this.weatherService = weatherService;
        this.appExecutors = appExecutors;
    }

    public LiveData<DayWeather> loadDayWeather(String cityName) {
        return new NetworkBoundResource<DayWeather>(appExecutors) {
        }
    }
}
