package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.view.OkHttpSingleActivity;

import dagger.Subcomponent;

/**
 * Description ${继承方式2，可以说是拓展}
 * Author zlz
 * Date 2017/1/22.
 */
@Subcomponent/*(modules = {DModule.class})*/
public interface OkHttpSingleComponent {

    void inject(OkHttpSingleActivity secondActivity);
}
