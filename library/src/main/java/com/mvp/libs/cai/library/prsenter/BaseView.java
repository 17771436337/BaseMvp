package com.mvp.libs.cai.library.prsenter;

import com.mvp.libs.cai.library.listener.BaseViewImp;
import com.mvp.libs.cai.library.listener.PresenterFactory;
import com.mvp.libs.cai.library.listener.ViewWithPresenter;

/**
 * 描述：
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/26
 */
public abstract class BaseView<P extends Presenter> implements BaseViewImp ,ViewWithPresenter<P> {

    protected PresenterDelegate<P> presenterDelegate = null;


    /**每个mvp必须要实现该方法，否则无法调用*/
    protected void onCreate(){
        presenterDelegate = new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));
        presenterDelegate.onCreate(this);
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
