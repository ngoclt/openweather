package life.coder.openweather.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.ui.forecast.ForecastActivity;
import life.coder.openweather.utils.OWCallback;
import life.coder.openweather.utils.OWHelper;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity implements OWCallback, Observer<OWCityWeather> {

    private static final int PERMISSION_REQUEST = 555;

    private TextView tvWeather, tvCityName,
            tvTemperature, tvHumidity,
            tvThermometer, tvWind,
            tvWeatherIcon;
    private Button btnMenu, btnBookmark;
    private HoursForecastFragment forecastFragment;


    private LinearLayout ltMainContainer;

    private long sunset = 0;
    private long sunrise = 0;

    private String cityName = "";
    private String longitude = "24.9488344", latitude = "60.1864416";
    DecimalFormat df;
    DecimalFormatSymbols sym;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        df = new DecimalFormat("#.#####");
        sym = DecimalFormatSymbols.getInstance();
        sym.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(sym);

        ltMainContainer = findViewById(R.id.lt_main_container);
        tvCityName = findViewById(R.id.tv_city_name);
        tvWeather = findViewById(R.id.tv_weather_description);
        tvTemperature = findViewById(R.id.tv_temperature_main);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvThermometer = findViewById(R.id.tv_thermometer);
        tvWind = findViewById(R.id.tv_wind);
        tvWeatherIcon = findViewById(R.id.tv_weather_icon);

        btnMenu = findViewById(R.id.btn_menu);
        btnBookmark = findViewById(R.id.btn_bookmark);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        forecastFragment = new HoursForecastFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getGPSLocation(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PERMISSION_DENIED) {
                return;
            }
        }
        getGPSLocation(this);
    }

    private void getGPSLocation(OWCallback callback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissionsNeeded = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissionsNeeded, PERMISSION_REQUEST);
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (lm != null) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    this.latitude = df.format(location.getLatitude());
                    this.longitude = df.format(location.getLongitude());
                    callback.onSuccess();
                } else {
                    callback.onFailure(getString(R.string.no_location));
                }
            } else {
                callback.onFailure(getString(R.string.no_location));
            }

            observeViewModel(viewModel);
        }
    }

    private void observeViewModel(MainViewModel viewModel) {
        viewModel.getOwCityWeatherLiveData(latitude, longitude, this)
                .observe(this, owCityWeather -> {
                    if (owCityWeather != null) {
                        updateUI(owCityWeather);
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
    public void onChanged(@Nullable OWCityWeather owCityWeather) {
        if (owCityWeather != null) {
            updateUI(owCityWeather);
        }
    }

    private void updateUI(OWCityWeather owCityWeather) {
        sunset = owCityWeather.getSys().getSunset();
        sunrise = owCityWeather.getSys().getSunrise();

        cityName = owCityWeather.getName();

        int backgroundId = OWHelper.getBackground(sunrise, sunset);
        ltMainContainer.setBackgroundResource(backgroundId);

        tvCityName.setText(cityName);

        if (owCityWeather.getWeather().size() > 0) {
            String description = OWHelper.getCapSentences(owCityWeather.getWeather().get(0).getDescription());
            tvWeather.setText(description);
            tvWeatherIcon.setText(OWHelper.getWeatherIcon(owCityWeather.getWeather().get(0).getId(), this, sunrise, sunset));
        } else {
            tvWeather.setText("-");
            tvWeatherIcon.setText("-");
        }

        tvTemperature.setText(Integer.toString(owCityWeather.getMain().getTemp().intValue()));
        tvHumidity.setText(Integer.toString(owCityWeather.getMain().getHumidity().intValue()));

        String minTemp = Integer.toString(owCityWeather.getMain().getTempMin().intValue());
        String maxTemp = Integer.toString(owCityWeather.getMain().getTempMax().intValue());
        tvThermometer.setText(minTemp.concat("/").concat(maxTemp));

        tvWind.setText(Float.toString(owCityWeather.getWind().getSpeed()).concat(" mps"));

        displayTodayForecastFragment();
    }

    private void displayTodayForecastFragment() {
        Bundle bundle = new Bundle(4);
        bundle.putString("longitude", longitude);
        bundle.putString("latitude", latitude);
        bundle.putLong("sunrise", sunrise);
        bundle.putLong("sunset", sunset);

        forecastFragment.setArguments(bundle);
        if (!getSupportFragmentManager().getFragments().contains(forecastFragment)) {
            getSupportFragmentManager().beginTransaction().add(R.id.ln_fragment_hours_forecast, forecastFragment).addToBackStack(null).commit();
        }
    }

    public void openForecastActivity(View target) {
        Intent intent = new Intent(this, ForecastActivity.class);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        intent.putExtra("sunrise", sunrise);
        intent.putExtra("sunset", sunset);
        intent.putExtra("cityName", cityName);

        startActivity(intent);
    }

    private void openBookmarkCityActivity(View target) {
    }
}
