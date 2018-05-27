package life.coder.openweather.utils;

import android.content.Context;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

import life.coder.openweather.R;

/**
 * Created by ngocle on 13/04/2018.
 */
public class OWHelper {

    public static String convertDateTime(Date date, String pattern) { //"E',' MMM dd kk:mm"
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
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

        // Check if still in the day
        long currentTime = System.currentTimeMillis() / 1000; // in seconds
        Boolean isDay = currentTime >= sunrise && currentTime < sunset;

        // If there is no sunrise and sunset time, just return the day icons
        if ((sunrise == 0 && sunset == 0) || isDay) {
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

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

}
