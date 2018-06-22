package com.mvp.libs.cai.library.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.libs.cai.library.Constant;
import com.mvp.libs.cai.library.listener.BaseContract;
import com.mvp.libs.cai.library.listener.PresenterFactory;
import com.mvp.libs.cai.library.listener.ViewWithPresenter;
import com.mvp.libs.cai.library.prsenter.Presenter;
import com.mvp.libs.cai.library.prsenter.PresenterDelegate;
import com.mvp.libs.cai.library.prsenter.ReflectionPresenterFactory;

/**
 * 描述：碎片的鸡肋，要保持数据在onViewCreated中进行初始化，否者可能会出现空指针
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/26
 *
 */
public abstract class BasicsFragment_v4<P extends Presenter> extends Fragment implements ViewWithPresenter<P> ,BaseContract.BaseViewIml{
    protected PresenterDelegate<P> presenterDelegate = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterDelegate = new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));
        if (savedInstanceState != null) {
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(Constant.PRESENTER_KEY));
        }
        presenterDelegate.onCreate(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constant.PRESENTER_KEY, presenterDelegate.onSaveInstanceState());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterDelegate.onDestroy();
    }


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
