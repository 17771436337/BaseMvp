package com.mvp.libs.cai.library.prsenter;

import android.support.annotation.Nullable;

import com.mvp.libs.cai.library.annotation.RequirePresenter;
import com.mvp.libs.cai.library.listener.PresenterFactory;

/**
 * Created by Jam on 2016/12/6.
 */

public class ReflectionPresenterFactory<P extends Presenter> implements PresenterFactory<P> {

    private Class<P> presenterClass;




    @Nullable
    public static <P extends Presenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> viewClass) {


        RequirePresenter annotation = null;
        try {
            annotation = Class.forName(viewClass.getName()).getAnnotation(RequirePresenter.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class<P> presenterClass = (Class<P>) annotation.value();
            return presenterClass == null ? null : new ReflectionPresenterFactory<>(presenterClass);



        //noinspection unchecked
//        Class<P> presenterClass = annotation == null ? null : (Class<P>)annotation.value();
//        return presenterClass == null ? null : new ReflectionPresenterFactory<>(presenterClass);
    }

    public ReflectionPresenterFactory(Class<P> presenterClass) {
        this.presenterClass = presenterClass;
    }

    @Override
    public P createPresenter() {
        try {
            return presenterClass.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}