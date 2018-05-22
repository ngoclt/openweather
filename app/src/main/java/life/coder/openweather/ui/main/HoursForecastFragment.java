package life.coder.openweather.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.utils.OWCallback;
import life.coder.openweather.utils.OWHelper;

public class HoursForecastFragment extends Fragment implements OWCallback, Observer<OWForecast> {

    private String longitude, latitude;
    private long sunrise, sunset;

    private HoursForecastViewModel viewModel;
    private HoursForecastAdapter adapter;

    private RecyclerView rcForecast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        longitude = getArguments().getString("longitude");
        latitude = getArguments().getString("latitude");
        sunrise = getArguments().getLong("sunrise");
        sunset = getArguments().getLong("sunset");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hours_forecast_list, container, false);
        rcForecast = view.findViewById(R.id.rc_hours_forecast);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcForecast.setLayoutManager(mLinearLayoutManager);
        adapter = new HoursForecastAdapter(new ArrayList<>(), getContext(), sunrise, sunset);
        rcForecast.setAdapter(adapter);

        view.setBackgroundColor(OWHelper.getColorWithAlpha(Color.BLACK, 0.f));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel = ViewModelProviders.of(this).get(HoursForecastViewModel.class);
        observeViewModel(viewModel);
    }


    private void observeViewModel(HoursForecastViewModel viewModel) {

        viewModel.getOwForeCastLiveData(latitude, longitude, 40, this).observe(this,
                owForecast -> {
                    if (owForecast != null) {
                        updateUI(owForecast);
                    }
                });
    }

    @Override
    public void onChanged(@Nullable OWForecast owForecast) {
        if (owForecast != null) {
            updateUI(owForecast);
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(@Nullable String error) {

    }

    private void updateUI(OWForecast owForecast) {
        adapter.setData(owForecast.getList());
        adapter.notifyDataSetChanged();
    }
}
