package life.coder.openweather.ui.bookmarkcity;

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

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    class BookmarkViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ltContainer;
        private TextView tvCityName, tvTemperature, tvWeatherIcon, tvWind, tvHumidity;

        BookmarkViewHolder(View itemView) {
            super(itemView);

            ltContainer = itemView.findViewById(R.id.lt_daily_forecast);
            ltContainer.setBackgroundColor(OWHelper.getColorWithAlpha(Color.BLACK, 0.4f));
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            tvTemperature = itemView.findViewById(R.id.tv_temperature_main);
            tvWeatherIcon = itemView.findViewById(R.id.tv_weather_icon);
            tvWind = itemView.findViewById(R.id.tv_wind);
            tvHumidity = itemView.findViewById(R.id.tv_humidity);
        }

        public void bindItem(OWCityWeather item, String icon) {
            tvCityName.setText(item.getName());
            tvTemperature.setText(Integer.toString(item.getMain().getTemp().intValue()));
            tvWeatherIcon.setText(icon);
            tvWind.setText(Float.toString(item.getWind().getSpeed()).concat(" mps"));
            tvHumidity.setText(Integer.toString(item.getMain().getHumidity().intValue()));
        }
    }

    private List<OWCityWeather> data;
    private Context context;

    BookmarkAdapter(List<OWCityWeather> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(List<OWCityWeather> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_item, parent, false);
        return new BookmarkViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        OWCityWeather item = data.get(position);
        String icon = OWHelper.getWeatherIcon(item.getWeather().get(0).getId(), context, item.getSys().getSunrise(), item.getSys().getSunset());
        holder.bindItem(item, icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
