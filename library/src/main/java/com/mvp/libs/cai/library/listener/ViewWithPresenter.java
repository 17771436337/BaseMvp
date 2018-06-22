package com.mvp.libs.cai.library.listener;


import com.mvp.libs.cai.library.prsenter.Presenter;

/**
 * 代理接口
 * Created by Administrator on 2017/11/27.
 */

public interface ViewWithPresenter<P extends Presenter> {
    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    PresenterFactory<P> getPresenterFactory();


    /**
     * 设置创建Presenter的工厂
     * @param PresenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterFactory<P> factory);

    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getPresenter();

}
