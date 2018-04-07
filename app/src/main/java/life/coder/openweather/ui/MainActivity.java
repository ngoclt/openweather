package life.coder.openweather.ui;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCity;
import life.coder.openweather.utils.OWCallback;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity implements OWCallback {

    private static final int PERMISSION_REQUEST = 555;
    private TextView tvWeather, tvCityName,
            tvTemperature, tvHumidity,
            tvPressure, tvVisibility,
            tvWeatherIcon, tvNoPermission;
    private Button btnRefresh;
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
        btnRefresh = findViewById(R.id.btn_refresh);

        ltMainContainer.setOnClickListener(view -> {
            goToForeCast();
        });
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        observeViewModel(viewModel);
        getLatlong(this);

    }

    private void goToForeCast() {
        Intent foreCastIntent = new Intent(this, ForecastActivity.class);
        foreCastIntent.putExtra("lat", latitude);
        foreCastIntent.putExtra("lon", longitude);
        startActivity(foreCastIntent);
    }

    private void observeViewModel(MainActivityViewModel viewModel) {

        viewModel.getOwCityWeatherLiveData(latitude, longitude, this).observe(this,
                owCityWeather -> {
                    if (owCityWeather != null) {
                        setInfo(owCityWeather);
                    }
                });
    }

    private void setInfo(OWCity owCityWeather) {
        sunSet = owCityWeather.getSys().getSunset();
        sunRise = owCityWeather.getSys().getSunrise();

        setCityName(owCityWeather.getName());
        setWeatherCondition(owCityWeather.getWeather().get(0).getDescription());
        setTemperature(Integer.toString(owCityWeather.getMain().getTemp()));
        setHumidity(Integer.toString(owCityWeather.getMain().getHumidity()));
        setPressure(Integer.toString(owCityWeather.getMain().getPressure()));
        setVisibility(Integer.toString(owCityWeather.getVisibility()));
        setContainer(owCityWeather.getMain().getTemp());
        setWeatherIcon(owCityWeather.getWeather().get(0).getId());
    }

    private void setContainer(int temp) {

        switch (temp / 10) {
            case -3:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.extremelyCold));
                break;
            case -2:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.veryCold));
                break;
            case -1:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.normalCold));
                break;
            case 0:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.justCold));
                break;
            case 1:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.normal));
                break;
            case 2:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.warm));
                break;
            case 3:
                ltMainContainer.setBackgroundColor(getResources().getColor(R.color.hot));
                break;
            default:
                break;
        }

    }

    private void setCityName(String cityName) {
        tvCityName.setText(cityName);
    }

    private void setWeatherCondition(String weatherCondition) {
        tvWeather.setText(weatherCondition);
    }

    private void setTemperature(String temperature) {
        tvTemperature.setText(temperature.concat(getString(R.string.Celcius)));
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

    private void setWeatherIcon(int id) {
        long currentTime = System.currentTimeMillis();
        if (currentTime > sunRise && currentTime < sunSet) {
            switch (id / 100) {
                case 2:
                    tvWeatherIcon.setText(getString(R.string.ThunderStorm_day));
                    break;
                case 3:
                    tvWeatherIcon.setText(getString(R.string.Drizzle_day));
                    break;
                case 5:
                    tvWeatherIcon.setText(getString(R.string.Rain_day));
                    break;
                case 6:
                    tvWeatherIcon.setText(getString(R.string.Snow_day));
                    break;
                case 7:
                    tvWeatherIcon.setText(getString(R.string.Atmosphere));
                    break;
                case 8:
                    if (id == 800) {
                        tvWeatherIcon.setText(getString(R.string.Clear_day));
                    } else {
                        tvWeatherIcon.setText(getString(R.string.Cloudy_day));
                    }
                    break;
                default:
                    break;
            }
        } else {
            switch (id / 100) {
                case 2:
                    tvWeatherIcon.setText(getString(R.string.ThunderStorm_night));
                    break;
                case 3:
                    tvWeatherIcon.setText(getString(R.string.Drizzle_night));
                    break;
                case 5:
                    tvWeatherIcon.setText(getString(R.string.Rain_night));
                    break;
                case 6:
                    tvWeatherIcon.setText(getString(R.string.Snow_night));
                    break;
                case 7:
                    tvWeatherIcon.setText(getString(R.string.Atmosphere));
                    break;
                case 8:
                    if (id == 800) {
                        tvWeatherIcon.setText(getString(R.string.Clear_night));
                    } else {
                        tvWeatherIcon.setText(getString(R.string.Cloudy_night));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void getLatlong(OWCallback callback) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissionsNeeded = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissionsNeeded, PERMISSION_REQUEST);
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lm != null) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    setLatitude(df.format(location.getLatitude()));
                    setLongitude(df.format(location.getLongitude()));
                    viewModel.getOwCityWeatherLiveData(latitude, longitude, this);
                } else {
                    callback.onFailure(getString(R.string.No_location));
                    return;
                }
                callback.onSuccess(null);
            } else {
                callback.onFailure(getString(R.string.No_location));
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PERMISSION_DENIED) {
                displayNoPermission(getString(R.string.Permission_denied));
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
        if (btnRefresh.getAnimation() != null) {
            btnRefresh.clearAnimation();
        }
        btnRefresh.setOnClickListener(view -> {
            btnRefresh.setAnimation(refreshAnim);
            btnRefresh.startAnimation(refreshAnim);
            viewModel.getOwCityWeatherLiveData(latitude, longitude, this);
        });
    }

    private void displayNoPermission(String error) {
        ltMainContainer.setVisibility(View.GONE);
        tvNoPermission.setVisibility(View.VISIBLE);
        tvNoPermission.setText(error);
    }

    @Override
    public void onSuccess(@Nullable Class result) {
        displayContent();
    }

    @Override
    public void onFailure(@Nullable String error) {
        displayNoPermission(error);
        if (btnRefresh.getAnimation() != null) {
            btnRefresh.clearAnimation();
        }
    }
}
