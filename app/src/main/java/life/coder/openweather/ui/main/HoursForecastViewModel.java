package life.coder.openweather.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.repository.OWRepository;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by Ngoc Le on 22/05/2018.
 */

public class HoursForecastViewModel extends AndroidViewModel {

    public HoursForecastViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<OWForecast> getOwForeCastLiveData(String lat, String lon, int limit, OWCallback callback) {
        return OWRepository.getInstance().getOWForeCast(lat, lon, limit, callback);
    }
}
