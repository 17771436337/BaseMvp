package com.mvp.libs.cai.library.prsenter;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.mvp.libs.cai.library.listener.BaseContract;


/**
 * Created by Administrator on 2017/11/27.
 */

public class Presenter<View> implements BaseContract.BasePresenterIml<View> {

    private View mView;
    @Override
    public void attachView(View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void onCreatePresenter(@Nullable Bundle savedState) {

    }

    @Override
    public void onDestroyPresenter() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public View getView() {
        return mView;
    }
}
