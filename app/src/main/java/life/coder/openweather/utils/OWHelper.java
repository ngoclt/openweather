package life.coder.openweather.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import life.coder.openweather.R;

/**
 * Created by TheGayLord on 13/04/2018.
 */
public class OWHelper {

    public static String convertDateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("E',' MMM dd kk:mm");
        return formatter.format(testDate);
    }

    public static String getCapSentences(String original) {
        if (original.isEmpty()) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static int getBackground(long sunrise, long sunset) {

        long currentTime = System.currentTimeMillis() / 1000; // in seconds
        long deltaTime = 2 * 60 * 60; // 2 hours in seconds

        if (currentTime >= sunrise - deltaTime && currentTime < sunrise + deltaTime) {
            return R.drawable.bg_morning;
        } else if (currentTime >= sunrise + deltaTime && currentTime < sunset - deltaTime) {
            return R.drawable.bg_day;
        } else if (currentTime >= sunset - deltaTime && currentTime < sunset + deltaTime) {
            return R.drawable.bg_evening;
        }

        return R.drawable.bg_night;
    }

    public static String getWeatherIcon(int id, Context context, long sunrise, long sunset) {
        long currentTime = System.currentTimeMillis() / 1000; // in seconds
        if (currentTime >= sunrise && currentTime < sunset) {
            switch (id / 100) {
                case 2:
                    return context.getString(R.string.thunder_storm_day);
                case 3:
                    return context.getString(R.string.drizzle_day);
                case 5:
                    return context.getString(R.string.rain_day);
                case 6:
                    return context.getString(R.string.snow_day);
                case 7:
                    return context.getString(R.string.atmosphere);
                case 8:
                    if (id == 800) {
                        return context.getString(R.string.clear_day);
                    } else {
                        return context.getString(R.string.cloudy_day);
                    }
                default:
                    return "";
            }
        } else {
            switch (id / 100) {
                case 2:
                    return context.getString(R.string.thunder_storm_night);
                case 3:
                    return context.getString(R.string.drizzle_night);
                case 5:
                    return context.getString(R.string.rain_night);
                case 6:
                    return context.getString(R.string.snow_night);
                case 7:
                    return context.getString(R.string.atmosphere);
                case 8:
                    if (id == 800) {
                        return context.getString(R.string.clear_night);
                    } else {
                        return context.getString(R.string.cloudy_night);
                    }
                default:
                    return "";
            }
        }
    }

    public static int getTempColor(Double temp, Context context) {

        switch ((int) Math.round(temp / 10)) {
            case -3:
                return context.getResources().getColor(R.color.extremelyCold);
            case -2:
                return context.getResources().getColor(R.color.veryCold);
            case -1:
                return context.getResources().getColor(R.color.normalCold);
            case 0:
                return context.getResources().getColor(R.color.justCold);
            case 1:
                return context.getResources().getColor(R.color.normal);
            case 2:
                return context.getResources().getColor(R.color.warm);
            case 3:
                return context.getResources().getColor(R.color.hot);
            default:
                return 0;
        }

    }
}
