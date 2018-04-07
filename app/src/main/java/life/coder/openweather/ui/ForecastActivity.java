package life.coder.openweather.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.utils.OWCallback;
import timber.log.Timber;

/**
 * Created by thegaylord on 08/12/2017.
 */

public class ForecastActivity extends AppCompatActivity implements OWCallback, Observer<OWForecast> {

    String longitude, latitude;
    ForecastActivityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            longitude = intent.getExtras().getString("lon");
            latitude = intent.getExtras().getString("lat");
        }
        viewModel = ViewModelProviders.of(this).get(ForecastActivityViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(ForecastActivityViewModel viewModel) {

        viewModel.getOwForeCastLiveData(latitude, longitude, this).observe(this,
                owForecast -> {
                    if (owForecast != null) {
                        setInfo(owForecast);
                    }
                });
    }

    private void setInfo(OWForecast owForecast) {
        Timber.i("#######%s", owForecast.toString());
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure(@Nullable String error) {
    }

    @Override
    public void onChanged(@Nullable OWForecast owForecast) {
        if (owForecast != null) {
            setInfo(owForecast);
        }
    }
}
