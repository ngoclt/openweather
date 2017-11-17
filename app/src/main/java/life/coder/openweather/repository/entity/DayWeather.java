package life.coder.openweather.repository.entity;

import android.arch.persistence.room.Entity;

import java.util.List;

/**
 * Created by thegaylord on 16/11/2017.
 */


@Entity(primaryKeys = {"name"})
public class DayWeather {

    private List<DayWeatherDescription> weather;
    private DayWeatherDetail main;
    private String name;

    public DayWeather() {
    }

    public List<DayWeatherDescription> getWeather() {
        return weather;
    }

    public DayWeatherDetail getMain() {
        return main;
    }

    public static class DayWeatherDetail {

        private int temp;
        private int pressure;
        private int humidity;
        private int temp_min;
        private int temp_max;

        public DayWeatherDetail() {
        }

        public int getTemp() {
            return temp;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getTemp_min() {
            return temp_min;
        }

        public int getTemp_max() {
            return temp_max;
        }
    }

    static class DayWeatherDescription {

        private int id;
        private String main;
        private String description;
        private String icon;

        public DayWeatherDescription() {
        }

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }
}
