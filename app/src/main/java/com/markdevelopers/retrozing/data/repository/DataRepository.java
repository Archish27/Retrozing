package com.markdevelopers.retrozing.data.repository;

import com.markdevelopers.retrozing.data.remote.models.DataWrapper;

import rx.Observable;

/**
 * Created by Archish on 2/23/2017.
 */

public interface DataRepository {
    Observable<DataWrapper> fetchData();
}
