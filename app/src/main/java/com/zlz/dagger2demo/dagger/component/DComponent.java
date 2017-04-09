package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.module.DModule;
import com.zlz.dagger2demo.dagger.view.FirstActivity;

import dagger.Component;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/4/7.
 */
@Component(modules={DModule.class})
public interface DComponent {
    void inject(FirstActivity firstActivity);
}
