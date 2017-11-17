package life.coder.openweather.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import life.coder.openweather.repository.dao.DayWeatherDAO;
import life.coder.openweather.repository.entity.DayWeather;

/**
 * Created by thegaylord on 16/11/2017.
 */

@Database(entities = {DayWeather.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    abstract public DayWeatherDAO dayWeatherDAO();

}
