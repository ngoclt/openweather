package life.coder.openweather.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by thegaylord on 08/12/2017.
 */

public class ForecastActivity extends AppCompatActivity implements OWCallback, Observer<OWForecast> {

    String longitude, latitude;
    ForecastActivityViewModel viewModel;
    GraphView gvForecast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);

        gvForecast = findViewById(R.id.gv_forecast);

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
                        setInfo();
                    }
                });
    }

    private void setInfo() {
        LineGraphSeries<DataPoint> series = viewModel.getPoint();
        gvForecast.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        gvForecast.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values>
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values<br />
                    return super.formatLabel(value, isValueX) + ";";
                }
            }
        });
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
            setInfo();
            Log.i("#######", String.valueOf(owForecast.getList().size()));
        }
    }
}
