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

    public static String getWeatherIcon(int id, Context context, long sunSet, long sunRise) {
        long currentTime = System.currentTimeMillis();
        if (currentTime > sunRise && currentTime < sunSet) {
            switch (id / 100) {
                case 2:
                    return context.getString(R.string.ThunderStorm_day);
                case 3:
                    return context.getString(R.string.Drizzle_day);
                case 5:
                    return context.getString(R.string.Rain_day);
                case 6:
                    return context.getString(R.string.Snow_day);
                case 7:
                    return context.getString(R.string.Atmosphere);
                case 8:
                    if (id == 800) {
                        return context.getString(R.string.Clear_day);
                    } else {
                        return context.getString(R.string.Cloudy_day);
                    }
                default:
                    return "";
            }
        } else {
            switch (id / 100) {
                case 2:
                    return context.getString(R.string.ThunderStorm_night);
                case 3:
                    return context.getString(R.string.Drizzle_night);
                case 5:
                    return context.getString(R.string.Rain_night);
                case 6:
                    return context.getString(R.string.Snow_night);
                case 7:
                    return context.getString(R.string.Atmosphere);
                case 8:
                    if (id == 800) {
                        return context.getString(R.string.Clear_night);
                    } else {
                        return context.getString(R.string.Cloudy_night);
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
