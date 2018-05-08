package life.coder.openweather.ui;

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
import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.utils.OWHelper;

/**
 * Created by TheGayLord on 13/04/2018.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private List<OWForecast.ListBean> data;
    private Context context;
    private long sunSet = 0;
    private long sunRise = 0;

    ForecastAdapter(List<OWForecast.ListBean> data, Context context, long sunRise, long sunSet) {
        this.data = data;
        this.context = context;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
    }

    public void setData(List<OWForecast.ListBean> data) {
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
        OWForecast.ListBean item = data.get(position);
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, sunRise, sunSet);
        int backGroundColor = OWHelper.getTempColor(item.getMain().getTemp(), context);
        holder.bindItem(item, icon, backGroundColor);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

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

    public void bindItem(OWForecast.ListBean item, String icon, int backGroundColor) {
        ctContainer.setBackgroundColor(backGroundColor);
        tvForecastDate.setText(OWHelper.convertDateTime(item.getDt_txt()));
        tvForecastIcon.setText(icon);
        tvForecastTemp.setText(String.valueOf(item.getMain().getTemp()));
    }
}

