package abhishekint.com.newsappupdate;

import android.app.Application;

import abhishekint.com.newsappupdate.Component.ComponentInterface;
import abhishekint.com.newsappupdate.Component.DaggerComponentInterface;
import abhishekint.com.newsappupdate.modules.ApplicationContextModule;
import abhishekint.com.newsappupdate.utils.TimberRelease;
import timber.log.Timber;

/**
 * Created by abhishek on 14-03-2018.
 */

public class NewsApplication extends Application {

    ComponentInterface componentInterface;
    @Override
    public void onCreate() {
        super.onCreate();
        componentInterface = DaggerComponentInterface.builder()
                .applicationContextModule(new ApplicationContextModule(this)).build();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new TimberRelease());
    }

    public ComponentInterface getNewsAppComponent() {
        return componentInterface;
    }
}
