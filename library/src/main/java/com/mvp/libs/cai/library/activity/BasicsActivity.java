package com.mvp.libs.cai.library.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mvp.libs.cai.library.Constant;
import com.mvp.libs.cai.library.listener.BaseContract;
import com.mvp.libs.cai.library.listener.PresenterFactory;
import com.mvp.libs.cai.library.listener.ViewWithPresenter;
import com.mvp.libs.cai.library.prsenter.Presenter;
import com.mvp.libs.cai.library.prsenter.PresenterDelegate;
import com.mvp.libs.cai.library.prsenter.ReflectionPresenterFactory;

/**
 * 描述：集成于Activity
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/26
 */
public abstract class BasicsActivity<P extends Presenter> extends Activity implements ViewWithPresenter<P> ,BaseContract.BaseViewIml{
    protected PresenterDelegate<P> presenterDelegate = null;
    //-------------------------------------------------------------------周期相关代码----------------------------------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterDelegate = new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));
        if (savedInstanceState != null) {
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(Constant.PRESENTER_KEY));
        }
        presenterDelegate.onCreate(this);
    }


    @Override
    protected void onDestroy() {
        presenterDelegate.onDestroy();
        super.onDestroy();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constant.PRESENTER_KEY, presenterDelegate.onSaveInstanceState());
    }

    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------

    @Override
    public PresenterFactory<P> getPresenterFactory() {
        if (presenterDelegate != null){
            return  presenterDelegate.getPresenterFactory();
        }else{
            return null;
        }

    }

    @Override
    public void setPresenterFactory(PresenterFactory<P> factory) {
        if (presenterDelegate != null) {
            presenterDelegate.setPresenterFactory(factory);
        }
    }

    @Override
    public P getPresenter() {
        if (presenterDelegate != null) {
            return presenterDelegate.getPresenter();
        }else{
            return null;
        }
    }

    @Override
    public void showError(String msg, boolean isLoadMore) {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showProgressUI(boolean isShow) {

    }
}
