package com.markdevelopers.retrozing.data.remote;

import com.markdevelopers.retrozing.data.remote.models.DataWrapper;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Archish on 2/23/2017.
 */

public interface DataRestService {
    @GET("data.php")
    Observable<DataWrapper> fetchData();
}
