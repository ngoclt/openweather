package life.coder.openweather.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.repository.OWRepository;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by thegaylord on 30/11/2017.
 */

public class MainActivityViewModel extends AndroidViewModel {

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<OWCityWeather> getOwCityWeatherLiveData(String lat, String lon, OWCallback callback) {
        return OWRepository.getInstance().getOWCityWeather(lat, lon, callback);
    }

    LiveData<OWForecast> getOwForeCastLiveData(String lat, String lon, int limit, OWCallback callback) {
        return OWRepository.getInstance().getOWForeCast(lat, lon, limit, callback);
    }
}
