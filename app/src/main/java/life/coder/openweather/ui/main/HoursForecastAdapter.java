package life.coder.openweather.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.utils.OWHelper;


public class HoursForecastAdapter extends RecyclerView.Adapter<HoursForecastAdapter.HoursForecastViewHolder> {

    class HoursForecastViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTemperature, tvWeatherIcon, tvWind, tvHumidity;
        private LinearLayout ltContainer;

        HoursForecastViewHolder(View itemView) {
            super(itemView);

            tvTemperature = itemView.findViewById(R.id.tv_temperature_main);
            tvWeatherIcon = itemView.findViewById(R.id.tv_weather_icon);
            tvWind = itemView.findViewById(R.id.tv_wind);
            tvHumidity = itemView.findViewById(R.id.tv_humidity);
            ltContainer = itemView.findViewById(R.id.lt_today_forecast);
            ltContainer.setBackgroundColor(OWHelper.getColorWithAlpha(Color.BLACK, 0.4f));
        }

        public void bindItem(OWCityWeather item, String icon) {
            tvTemperature.setText(Integer.toString(item.getMain().getTemp().intValue()));
            tvWeatherIcon.setText(icon);
            tvWind.setText(Float.toString(item.getWind().getSpeed()).concat(" mps"));
            tvHumidity.setText(Integer.toString(item.getMain().getHumidity().intValue()));
        }
    }

    private List<OWCityWeather> data;
    private Context context;
    private long sunset = 0;
    private long sunrise = 0;

    HoursForecastAdapter(List<OWCityWeather> data, Context context, long sunrise, long sunset) {
        this.data = data;
        this.context = context;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public void setData(List<OWCityWeather> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HoursForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hours_forecast_item, parent, false);
        return new HoursForecastViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursForecastViewHolder holder, int position) {
        OWCityWeather item = data.get(position);
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, sunrise, sunset);
        holder.bindItem(item, icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}