package com.mvp.libs.cai.basemvp;

import android.os.Bundle;

import com.mvp.libs.cai.library.activity.BasicsAppCompatActivity;
import com.mvp.libs.cai.library.annotation.RequirePresenter;


@RequirePresenter(MainPresenter.class)
public class MainActivity extends BasicsAppCompatActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
