package life.coder.openweather.utils;

import android.support.annotation.Nullable;

/**
 * Created by thegaylord on 04/12/2017.
 */
public interface OWCallback {
    void onSuccess(@Nullable Class result);

    void onFailure(@Nullable String error);
}
