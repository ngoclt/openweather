package life.coder.openweather.ui.forecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.utils.OWHelper;

/**
 * Created by TheGayLord on 13/04/2018.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        private TextView tvForecastDate, tvForecastIcon, tvForecastTemp;
        private ConstraintLayout ctContainer;

        ForecastViewHolder(View itemView) {
            super(itemView);

            tvForecastDate = itemView.findViewById(R.id.tv_forecast_date);
            tvForecastIcon = itemView.findViewById(R.id.tv_forecast_icon);
            tvForecastTemp = itemView.findViewById(R.id.tv_forecast_temp);
            ctContainer = itemView.findViewById(R.id.ct_container);

        }

        public void bindItem(OWCityWeather item, String icon) {
            tvForecastDate.setText(OWHelper.convertDateTime(item.getDate()));
            tvForecastIcon.setText(icon);
            tvForecastTemp.setText(String.valueOf(item.getMain().getTemp()));
        }
    }

    private List<OWCityWeather> data;
    private Context context;
    private long sunset = 0;
    private long sunrise = 0;

    ForecastAdapter(List<OWCityWeather> data, Context context, long sunrise, long sunset) {
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
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecastviewholder, parent, false);
        return new ForecastViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        OWCityWeather item = data.get(position);
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, sunrise, sunset);
        holder.bindItem(item, icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

