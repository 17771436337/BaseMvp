package com.mvp.libs.cai.library.listener;

import com.mvp.libs.cai.library.prsenter.Presenter;

/**
 * Presenter工厂接口
 * Created by Jam on 2016/12/6.
 */
public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
