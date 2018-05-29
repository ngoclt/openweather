package life.coder.openweather.ui.bookmarkcity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.repository.OWRepository;
import life.coder.openweather.utils.OWCallback;

public class BookmarkViewModel extends AndroidViewModel {

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<OWCityWeather> getOwCityWeatherLiveData(String cityName, OWCallback callback) {
        return OWRepository.getInstance().getOWCityWeather(cityName, callback);
    }
}