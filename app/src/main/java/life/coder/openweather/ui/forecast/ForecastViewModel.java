package life.coder.openweather.ui.forecast;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import life.coder.openweather.api.model.OWDailyForecast;
import life.coder.openweather.api.model.OWDailyWeather;
import life.coder.openweather.repository.OWRepository;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by ngocle on 23/05/2017.
 */

public class ForecastViewModel extends AndroidViewModel {

    public ForecastViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<OWDailyForecast> getDailyForecastLiveData(String lat, String lon, int limit, OWCallback callback) {
        return OWRepository.getInstance().getDailyForeCast(lat, lon, limit, callback);
    }

}
