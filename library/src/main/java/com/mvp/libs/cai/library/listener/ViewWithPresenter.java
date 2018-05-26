package com.mvp.libs.cai.library.listener;


import com.mvp.libs.cai.library.prsenter.Presenter;

/**
 * Created by Administrator on 2017/11/27.
 */

public interface ViewWithPresenter<P extends Presenter> {
    PresenterFactory<P> getPresenterFactory();

    void setPresenterFactory(PresenterFactory<P> factory);

    P getPresenter();

}
