package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.dagger.bean.C;

import dagger.Component;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/1/19.
 */
@Component
public interface BaseComponent {

    void inject(BaseActivity activity);

    C getC();
}
