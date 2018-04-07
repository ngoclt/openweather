package life.coder.openweather.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import life.coder.openweather.api.WeatherService;
import life.coder.openweather.api.model.OWCity;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.di.component.DaggerNetComponent;
import life.coder.openweather.di.module.NetModule;
import life.coder.openweather.utils.OWCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by thegaylord on 30/11/2017.
 */

public class OWRepository {

    private WeatherService weatherService;
    private static OWRepository _instance;
    @Inject
    Retrofit retrofit;

    private OWRepository() {
        DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build().inject(this);

        weatherService = retrofit.create(WeatherService.class);
    }

    public static OWRepository getInstance() {
        if (_instance == null) {
            _instance = new OWRepository();
        }
        return _instance;
    }

    public LiveData<OWCity> getOWCityWeather(String lat, String lon, OWCallback callback) {
        final MutableLiveData<OWCity> data = new MutableLiveData<>();

        weatherService.getDayWeather(lat, lon)
                .enqueue(new Callback<OWCity>() {
                    @Override
                    public void onResponse(Call<OWCity> call, Response<OWCity> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                            callback.onSuccess(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<OWCity> call, Throwable t) {
                        callback.onFailure(null);
                    }
                });
        return data;
    }

    public LiveData<OWForecast> getOWForeCast(String lat, String lon, OWCallback callback) {
        final MutableLiveData<OWForecast> data = new MutableLiveData<>();

        weatherService.getForeCast(lat, lon)
                .enqueue(new Callback<OWForecast>() {
                    @Override
                    public void onResponse(Call<OWForecast> call, Response<OWForecast> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                            callback.onSuccess(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<OWForecast> call, Throwable t) {
                        callback.onFailure(null);
                    }
                });
        return data;
    }

}
