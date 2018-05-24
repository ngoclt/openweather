package life.coder.openweather.api;

import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.api.model.OWDailyForecast;
import life.coder.openweather.api.model.OWForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ngocle on 16/11/2017.
 */

public interface WeatherService {

    @GET("weather")
    Call<OWCityWeather> getDayWeather(
            @Query("lat") String lat,
            @Query("lon") String lon);

    @GET("forecast")
    Call<OWForecast> getForeCast(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("cnt") int limit);

    @GET("forecast/daily")
    Call<OWDailyForecast> getDailyForeCast(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("cnt") int limit);
}
