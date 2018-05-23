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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by thegaylord on 08/12/2017.
 */

public class ForecastActivity extends AppCompatActivity implements OWCallback, Observer<OWForecast> {

    private LinearLayout ltMainContainer;
    String longitude, latitude;
    ForecastActivityViewModel viewModel;
    RecyclerView rcForecast;
    ForecastAdapter adapter;
    SwipeRefreshLayout ltRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);

        ltMainContainer = findViewById(R.id.lt_main_container);

        rcForecast = findViewById(R.id.rc_forecast);
        ltRefresh = findViewById(R.id.lt_refresh);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rcForecast.setLayoutManager(mLinearLayoutManager);
        long sunRise = 0;
        long sunSet = 0;

        if (getIntent() != null && getIntent().getExtras() != null) {
            sunRise = getIntent().getExtras().getLong("sunRise", 0);
            sunSet = getIntent().getExtras().getLong("sunSet", 0);
        }

        adapter = new ForecastAdapter(new ArrayList<>(), this, sunRise, sunSet);
        rcForecast.setAdapter(adapter);

        ltRefresh.setOnRefreshListener(() ->
                viewModel.getOwForeCastLiveData(latitude, longitude, 40, this));
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

        viewModel.getOwForeCastLiveData(latitude, longitude, 0, this).observe(this,
                owForecast -> {
                    if (owForecast != null) {
                        setInfo(owForecast.getList());
                    }
                });
    }

    private void setInfo(List<OWCityWeather> owForecastList) {
        adapter.setData(owForecastList);
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
    public void onChanged(@Nullable OWForecast owForecast) {
        if (owForecast != null) {
            setInfo(owForecast.getList());
        }
    }
}
