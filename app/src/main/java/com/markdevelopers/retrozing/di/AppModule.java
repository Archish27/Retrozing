package com.markdevelopers.retrozing.di;

import com.markdevelopers.retrozing.common.Config;
import com.markdevelopers.retrozing.data.implementation.DataImpl;
import com.markdevelopers.retrozing.data.remote.DataRestService;
import com.markdevelopers.retrozing.data.repository.DataRepository;
import com.markdevelopers.retrozing.network.RxErrorHandlingCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Archish on 2/23/2017.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    DataRepository provideDataRepository(DataRestService dataRestService) {
        return new DataImpl(dataRestService);
    }

    @Provides
    @Singleton
    DataRestService provideDataRestService(Retrofit retrofit) {
        return retrofit.create(DataRestService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
    }
}
