package com.markdevelopers.retrozing.di;

import com.markdevelopers.retrozing.data.repository.DataRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Archish on 2/23/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    DataRepository dataRepository();
}
