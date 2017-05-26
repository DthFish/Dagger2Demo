package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.AppComponent;
import com.zlz.dagger2demo.dagger.PerActivity;
import com.zlz.dagger2demo.dagger.view.OkHttpSingleActivity;

import dagger.Component;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/5/26.
 */
@PerActivity
@Component(dependencies = {AppComponent.class})
public interface OkHttpSingle2Component {
    void inject(OkHttpSingleActivity okHttpSingleActivity);
}
