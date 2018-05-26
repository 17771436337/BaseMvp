package com.mvp.libs.cai.library.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.libs.cai.library.listener.PresenterFactory;
import com.mvp.libs.cai.library.listener.ViewWithPresenter;
import com.mvp.libs.cai.library.prsenter.Presenter;
import com.mvp.libs.cai.library.prsenter.PresenterDelegate;
import com.mvp.libs.cai.library.prsenter.ReflectionPresenterFactory;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/26
 */
public abstract class BasicsFragment<P extends Presenter> extends Fragment implements ViewWithPresenter<P> {
    protected PresenterDelegate<P> presenterDelegate = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenterDelegate = new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));
        presenterDelegate.onCreate(this);
        return super.onCreateView(inflater, container, savedInstanceState);
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
}
