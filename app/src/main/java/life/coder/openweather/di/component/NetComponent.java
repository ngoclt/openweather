package life.coder.openweather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import life.coder.openweather.di.module.NetModule;
import life.coder.openweather.repository.OWRepository;

/**
 * Created by thegaylord on 30/11/2017.
 */

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {

    void inject(OWRepository owRepository);

}
