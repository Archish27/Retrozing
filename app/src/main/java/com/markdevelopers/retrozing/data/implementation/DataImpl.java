package com.markdevelopers.retrozing.data.implementation;

import com.markdevelopers.retrozing.data.remote.DataRestService;
import com.markdevelopers.retrozing.data.remote.models.DataWrapper;
import com.markdevelopers.retrozing.data.repository.DataRepository;

import rx.Observable;

/**
 * Created by Archish on 2/23/2017.
 */

public class DataImpl implements DataRepository {
    private DataRestService dataRestService;

    public DataImpl(DataRestService dataRestService) {
        this.dataRestService = dataRestService;
    }

    @Override
    public Observable<DataWrapper> fetchData() {
        return dataRestService.fetchData();
    }
}
