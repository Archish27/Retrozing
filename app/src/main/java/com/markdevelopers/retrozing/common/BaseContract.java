package com.markdevelopers.retrozing.common;

/**
 * Created by Archish on 12/19/2016.
 */

public interface BaseContract {
    interface BaseView {
        void onNetworkException(Throwable e);
    }
}
