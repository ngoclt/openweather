package life.coder.openweather.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import life.coder.openweather.api.model.OWForecast;
import life.coder.openweather.repository.OWRepository;
import life.coder.openweather.utils.OWCallback;

/**
 * Created by thegaylord on 08/12/2017.
 */

public class ForecastActivityViewModel extends AndroidViewModel {
    private LiveData<OWForecast> data;

    public ForecastActivityViewModel(@NonNull Application application) {
        super(application);

    }

    LiveData<OWForecast> getOwForeCastLiveData(String lat, String lon, OWCallback callback) {
        data = OWRepository.getInstance().getOWForeCast(lat, lon, callback);
        return data;
    }

    LineGraphSeries<DataPoint> getPoint() {
        if (data.getValue() != null) {
            return generateTemperaturePoint(data.getValue());
        }
        return null;
    }

    private LineGraphSeries<DataPoint> generateTemperaturePoint(OWForecast data) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        for (int i = 0; i < data.getList().size(); i++) {
            DataPoint point = new DataPoint(i, data.getList().get(i).getMain().getTemp());
            series.appendData(point, true, 40);
        }

        return series;
    }

}
