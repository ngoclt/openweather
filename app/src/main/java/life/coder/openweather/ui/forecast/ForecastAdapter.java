package life.coder.openweather.ui.forecast;

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
import life.coder.openweather.api.model.OWDailyWeather;
import life.coder.openweather.utils.OWHelper;

/**
 * Created by ngocle on 13/04/2018.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ltContainer;
        private TextView tvTemperature, tvWeatherIcon, tvWind, tvHumidity, tvForeCastTime;

        ForecastViewHolder(View itemView) {
            super(itemView);

            ltContainer = itemView.findViewById(R.id.lt_daily_forecast);
            ltContainer.setBackgroundColor(OWHelper.getColorWithAlpha(Color.BLACK, 0.4f));
            tvTemperature = itemView.findViewById(R.id.tv_temperature_main);
            tvWeatherIcon = itemView.findViewById(R.id.tv_weather_icon);
            tvForeCastTime = itemView.findViewById(R.id.tv_forecast_time);
            tvWind = itemView.findViewById(R.id.tv_wind);
            tvHumidity = itemView.findViewById(R.id.tv_humidity);
        }

        public void bindItem(OWDailyWeather item, String icon) {
            tvTemperature.setText(Integer.toString(item.getTemp().getDay().intValue()));
            tvWeatherIcon.setText(icon);
            tvWind.setText(Float.toString(item.getSpeed()).concat(" mps"));
            tvHumidity.setText(Integer.toString(item.getHumidity().intValue()));
            tvForeCastTime.setText(OWHelper.convertDateTime(item.getDate(), "EE, MMMM dd"));
        }
    }

    private List<OWDailyWeather> data;
    private Context context;

    ForecastAdapter(List<OWDailyWeather> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(List<OWDailyWeather> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_forecast_item, parent, false);
        return new ForecastViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        OWDailyWeather item = data.get(position);
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, 0, 0);
        holder.bindItem(item, icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

