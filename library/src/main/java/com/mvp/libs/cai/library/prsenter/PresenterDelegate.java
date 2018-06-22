package com.mvp.libs.cai.library.prsenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvp.libs.cai.library.Constant;
import com.mvp.libs.cai.library.listener.PresenterFactory;

/**
 * 代理实现类，用来管理Presenter的生命周期，还有和view之间的关联
 */

public class PresenterDelegate<P extends Presenter> {

    private PresenterFactory<P> presenterFactory;
    private P presenter;
    private boolean mIsAttachView;
    private Bundle mBundle;
    public PresenterDelegate(@Nullable PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }


    /**
     * 获取Presenter的工厂类
     *
     * @return PresenterFactory类型
     */
    @Nullable
    public PresenterFactory<P> getPresenterFactory() {
        return presenterFactory;
    }


    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory PresenterFactory类型
     */
    public void setPresenterFactory(@Nullable PresenterFactory<P> presenterFactory) {
        if (presenter != null) {
            throw new IllegalArgumentException("这个方法只能在getPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.presenterFactory = presenterFactory;
    }


    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     * 如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    public P getPresenter() {
        if (presenterFactory != null) {
            if (presenter == null) {
                presenter = presenterFactory.createPresenter();
                presenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(Constant.PRESENTER_KEY));
            }
        }
        return presenter;
    }


    /**绑定Presenter和view*/
    public void onCreate(Object view){
        getPresenter();
        if(this.presenter != null && !mIsAttachView){
            this.presenter.attachView(view);
            mIsAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        if (presenter != null && mIsAttachView) {
            presenter.detachView();
            mIsAttachView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        if (presenter != null) {
            onDetachMvpView();
            presenter.onDestroyPresenter();
            presenter = null;
        }
    }


    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (presenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            presenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(Constant.PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }


    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;
    }
}
