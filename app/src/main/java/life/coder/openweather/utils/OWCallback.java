package life.coder.openweather.utils;

import android.support.annotation.Nullable;

/**
 * Created by ngocle on 04/12/2017.
 */
public interface OWCallback {
    void onSuccess();

    void onFailure(@Nullable String error);
}
