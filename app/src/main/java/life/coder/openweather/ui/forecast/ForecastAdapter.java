package life.coder.openweather.ui.forecast;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        ForecastViewHolder(View itemView) {
            super(itemView);


            ltContainer = itemView.findViewById(R.id.lt_today_forecast);
            ltContainer.setBackgroundColor(OWHelper.getColorWithAlpha(Color.BLACK, 0.4f));


        }

        public void bindItem(OWDailyWeather item, String icon) {

        }
    }

    private List<OWDailyWeather> data;
    private Context context;
    private long sunset = 0;
    private long sunrise = 0;

    ForecastAdapter(List<OWDailyWeather> data, Context context, long sunrise, long sunset) {
        this.data = data;
        this.context = context;
        this.sunrise = sunrise;
        this.sunset = sunset;
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
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, sunrise, sunset);
        holder.bindItem(item, icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

