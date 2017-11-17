package life.coder.openweather.api;

import android.arch.lifecycle.LiveData;

import life.coder.openweather.repository.entity.DayWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thegaylord on 16/11/2017.
 */

public interface WeatherService {

    @GET
    LiveData<DayWeather> getDayWeather(@Query("q") String name);
}
