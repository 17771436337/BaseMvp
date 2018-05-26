package com.mvp.libs.cai.library.prsenter;

import com.mvp.libs.cai.library.listener.BasePresenterImp;

/**
 * Created by Administrator on 2017/11/27.
 */

public class Presenter<View> implements BasePresenterImp {
    View view;
    public View getView(){
        return view;
    }
    public void setView(View view){
        this.view = view;
    }
}
