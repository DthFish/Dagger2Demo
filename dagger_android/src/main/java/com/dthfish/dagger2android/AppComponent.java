package com.dthfish.dagger2android;

import dagger.Component;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MyApplication application);
}
