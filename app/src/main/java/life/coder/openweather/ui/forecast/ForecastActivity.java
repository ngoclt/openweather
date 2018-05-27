package life.coder.openweather.ui.forecast;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWDailyForecast;
import life.coder.openweather.api.model.OWDailyWeather;
import life.coder.openweather.utils.OWCallback;
import life.coder.openweather.utils.OWHelper;

/**
 * Created by ngocle on 08/12/2017.
 */

public class ForecastActivity extends AppCompatActivity implements OWCallback, Observer<OWDailyForecast> {

    private LinearLayout ltMainContainer;
    private String longitude, latitude;
    private String cityName;
    private long sunset = 0;
    private long sunrise = 0;

    ForecastViewModel viewModel;
    ForecastAdapter adapter;

    SwipeRefreshLayout ltRefresh;
    RecyclerView rcForecast;
    private TextView tvCityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);

        ltMainContainer = findViewById(R.id.lt_main_container);

        rcForecast = findViewById(R.id.rc_forecast);
        ltRefresh = findViewById(R.id.lt_refresh);
        tvCityName = findViewById(R.id.tv_city_name);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rcForecast.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            longitude = intent.getExtras().getString("longitude");
            latitude = intent.getExtras().getString("latitude");
            sunrise = intent.getExtras().getLong("sunrise");
            sunset = intent.getExtras().getLong("sunset");
            cityName = intent.getExtras().getString("cityName");

            int backgroundId = OWHelper.getBackground(sunrise, sunset);
            ltMainContainer.setBackgroundResource(backgroundId);

            tvCityName.setText(cityName);

            adapter = new ForecastAdapter(new ArrayList<>(), this);
            rcForecast.setAdapter(adapter);

            viewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
            observeViewModel(viewModel);

            ltRefresh.setOnRefreshListener(() ->
                    observeViewModel(viewModel)
            );
        }
    }

    private void observeViewModel(ForecastViewModel viewModel) {

        viewModel.getDailyForecastLiveData(latitude, longitude, 16, this).observe(this,
                owForecast -> {
                    if (owForecast != null) {
                        setInfo(owForecast.getList());
                    }
                });
    }

    private void setInfo(List<OWDailyWeather> owDailyForecasts) {
        adapter.setData(owDailyForecasts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        ltRefresh.setRefreshing(false);
    }

    @Override
    public void onFailure(@Nullable String error) {
        Log.e("NickHapper", error);
    }

    @Override
    public void onChanged(@Nullable OWDailyForecast owDailyForecast) {
        if (owDailyForecast != null) {
            setInfo(owDailyForecast.getList());
        }
    }

    public void clickOnBackBTN(View target) {
        onBackPressed();
    }
}
