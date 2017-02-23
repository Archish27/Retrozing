package com.markdevelopers.retrozing.common;

import android.app.Application;

import com.markdevelopers.retrozing.di.AppComponent;
import com.markdevelopers.retrozing.di.AppModule;
import com.markdevelopers.retrozing.di.DaggerAppComponent;

/**
 * Created by Archish on 2/23/2017.
 */

public class RetrozingApp extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initCompontent();
    }
    private void initCompontent()
    {
        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }
    public AppComponent getComponent() {
        return component;
    }
}
