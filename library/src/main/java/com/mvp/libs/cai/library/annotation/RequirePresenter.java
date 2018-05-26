package com.mvp.libs.cai.library.annotation;


import com.mvp.libs.cai.library.prsenter.Presenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 描述：Presenter的注解，需要作用于对应的acticity
 *
 * @author cmy
 * @e-mail 1020233514@qq.com
 * @time 2018/5/26
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePresenter {
    Class<? extends Presenter> value();
}
