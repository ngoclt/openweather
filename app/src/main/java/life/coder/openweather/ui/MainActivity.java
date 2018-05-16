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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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
            tvPressure, tvVisibility,
            tvWeatherIcon, tvNoPermission;

    private LinearLayout ltMainContainer;

    private long sunSet = 0;
    private long sunRise = 0;

    String longitude, latitude;
    DecimalFormat df;
    DecimalFormatSymbols sym;
    Animation refreshAnim;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        df = new DecimalFormat("#.#####");
        sym = DecimalFormatSymbols.getInstance();
        sym.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(sym);
        refreshAnim = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        refreshAnim.setRepeatCount(-1);
        refreshAnim.setDuration(1000);

        ltMainContainer = findViewById(R.id.lt_main_container);
        tvCityName = findViewById(R.id.tv_city_name);
        tvWeather = findViewById(R.id.tv_weather_description);
        tvTemperature = findViewById(R.id.tv_temperature_main);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvPressure = findViewById(R.id.tv_pressure);
        tvVisibility = findViewById(R.id.tv_visibility);
        tvWeatherIcon = findViewById(R.id.tv_weather_icon);
        tvNoPermission = findViewById(R.id.tv_no_permission);

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
        setPressure(Integer.toString(owCityWeather.getMain().getPressure()));
        setVisibility(Integer.toString(owCityWeather.getVisibility()));
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

    private void setPressure(String pressure) {
        tvPressure.setText(pressure);
    }

    private void setVisibility(String visibility) {
        tvVisibility.setText(visibility);
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
                    observeViewModel(viewModel);
                } else {
                    callback.onFailure(getString(R.string.no_location));
                    return;
                }
                callback.onSuccess();
            } else {
                callback.onFailure(getString(R.string.no_location));
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PERMISSION_DENIED) {
                displayNoPermission(getString(R.string.permission_denied));
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

    private void displayContent() {
        ltMainContainer.setVisibility(View.VISIBLE);
        tvNoPermission.setVisibility(View.GONE);
    }

    private void displayNoPermission(String error) {
        ltMainContainer.setVisibility(View.GONE);
        tvNoPermission.setVisibility(View.VISIBLE);
        tvNoPermission.setText(error);
    }

    @Override
    public void onSuccess() {
        displayContent();
    }

    @Override
    public void onFailure(@Nullable String error) {
        displayNoPermission(error);
    }

    @Override
    public void onChanged(@Nullable OWCity owCity) {
        if (owCity != null) {
            setInfo(owCity);
        }
    }
}
