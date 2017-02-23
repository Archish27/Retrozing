package com.markdevelopers.retrozing.activity;

import com.markdevelopers.retrozing.common.BaseContract;
import com.markdevelopers.retrozing.data.remote.models.DataWrapper;

/**
 * Created by Archish on 2/23/2017.
 */

public interface MainContract  {
    interface MainView extends BaseContract.BaseView{
        void onData(DataWrapper dataWrapper);
    }
    interface MainPresenter{
        void fetchData();
    }
}
