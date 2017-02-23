package com.markdevelopers.retrozing.activity;

import com.markdevelopers.retrozing.data.remote.models.DataWrapper;
import com.markdevelopers.retrozing.data.repository.DataRepository;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Archish on 2/23/2017.
 */

public class MainPresenter implements MainContract.MainPresenter {
    DataRepository dataRepository;
    MainContract.MainView mainView;

    public MainPresenter(DataRepository dataRepository, MainContract.MainView mainView) {
        this.dataRepository = dataRepository;
        this.mainView = mainView;
    }

    @Override
    public void fetchData() {
        dataRepository.fetchData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DataWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mainView!=null)
                            mainView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DataWrapper dataWrapper) {
                        mainView.onData(dataWrapper);
                    }
                });
    }

}
