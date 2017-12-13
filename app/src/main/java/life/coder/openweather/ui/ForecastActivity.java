package life.coder.openweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import life.coder.openweather.R;

/**
 * Created by thegaylord on 08/12/2017.
 */

public class ForecastActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        setContentView(R.layout.forecast);
        super.onCreate(savedInstanceState, persistentState);
    }
}
