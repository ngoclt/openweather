package life.coder.openweather.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import life.coder.openweather.repository.entity.DayWeather;

/**
 * Created by thegaylord on 16/11/2017.
 */

@Dao
public abstract class DayWeatherDAO {

    @Query("SELECT * FROM DayWeather "
            + "WHERE name = :owner ")
    public abstract LiveData<DayWeather> loadDayWeather(String city);
}
