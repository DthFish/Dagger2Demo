package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.dagger.bean.A;
import com.zlz.dagger2demo.dagger.module.AModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/1/18.
 */
@Singleton
@Component(modules={AModule.class})
public interface AComponent {
//    void inject(DetailActivity activity);
    void inject(BaseActivity activity);
    A providerA();
}
