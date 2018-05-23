package life.coder.openweather;

import android.app.Application;

import life.coder.openweather.di.component.NetComponent;

/**
 * Created by ngocle on 30/11/2017.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
