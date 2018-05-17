package life.coder.openweather.ui;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCity;
import life.coder.openweather.utils.OWCallback;
import life.coder.openweather.utils.OWHelper;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity implements OWCallback, Observer<OWCity> {

    private static final int PERMISSION_REQUEST = 555;
    private TextView tvWeather, tvCityName,
            tvTemperature, tvHumidity,
            tvThermometer, tvWind,
            tvWeatherIcon;

    private LinearLayout ltMainContainer;

    private long sunSet = 0;
    private long sunRise = 0;

    String longitude = "24.9488344", latitude = "60.1864416";
    DecimalFormat df;
    DecimalFormatSymbols sym;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

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

        ltMainContainer.setOnClickListener(view -> {
            goToForeCast();
        });

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLatlong(this);
    }

    private void goToForeCast() {
        Intent foreCastIntent = new Intent(this, ForecastActivity.class);
        foreCastIntent.putExtra("lat", latitude);
        foreCastIntent.putExtra("lon", longitude);
        startActivity(foreCastIntent);
    }

    private void observeViewModel(MainActivityViewModel viewModel) {
        viewModel.getOwCityWeatherLiveData(latitude, longitude, this)
                .observe(this, owCityWeather -> {
                    if (owCityWeather != null) {
                        setInfo(owCityWeather);
                    }
                });
    }

    private void setInfo(OWCity owCityWeather) {
        sunSet = owCityWeather.getSys().getSunset();
        sunRise = owCityWeather.getSys().getSunrise();

        setCityName(owCityWeather.getName());

        String description = OWHelper.getCapSentences(owCityWeather.getWeather().get(0).getDescription());
        setWeatherCondition(description);

        setTemperature(Integer.toString(owCityWeather.getMain().getTemp().intValue()));
        setHumidity(Integer.toString(owCityWeather.getMain().getHumidity()));

        String minTemp = Integer.toString(owCityWeather.getMain().getTempMin().intValue());
        String maxTemp = Integer.toString(owCityWeather.getMain().getTempMax().intValue());
        setThermometer(minTemp.concat("/").concat(maxTemp));

        setWind(Float.toString(owCityWeather.getWind().getSpeed()).concat(" mps"));
        setContainer(sunRise, sunSet);
        setWeatherIcon(owCityWeather.getWeather().get(0).getId(), sunRise, sunSet);


    }

    private void setWeatherIcon(int id, long sunRise, long sunSet) {
        tvWeatherIcon.setText(OWHelper.getWeatherIcon(id, this, sunRise, sunSet));
    }

    private void setContainer(long sunRise, long sunSet) {
        int backgroundId = OWHelper.getBackground(sunRise, sunSet);
        ltMainContainer.setBackgroundResource(backgroundId);
    }


    private void setCityName(String cityName) {
        tvCityName.setText(cityName);
    }

    private void setWeatherCondition(String weatherCondition) {
        tvWeather.setText(weatherCondition);
    }

    private void setTemperature(String temperature) {
        tvTemperature.setText(temperature);
    }

    private void setHumidity(String humidity) {
        tvHumidity.setText(humidity);
    }

    private void setThermometer(String thermometer) {
        tvThermometer.setText(thermometer);
    }

    private void setWind(String wind) {
        tvWind.setText(wind);
    }


    private void getLatlong(OWCallback callback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissionsNeeded = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissionsNeeded, PERMISSION_REQUEST);
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lm != null) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    setLatitude(df.format(location.getLatitude()));
                    setLongitude(df.format(location.getLongitude()));
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

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PERMISSION_DENIED) {
                return;
            }
        }
        getLatlong(this);
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure(@Nullable String error) {
    }

    @Override
    public void onChanged(@Nullable OWCity owCity) {
        if (owCity != null) {
            setInfo(owCity);
        }
    }
}
