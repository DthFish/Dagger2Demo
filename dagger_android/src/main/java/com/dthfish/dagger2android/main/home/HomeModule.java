package com.dthfish.dagger2android.main.home;

import com.dthfish.dagger2android.bean.MessageBean;

import dagger.Module;
import dagger.Provides;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/5.
 */
@Module
public class HomeModule {
    @Provides
    public MessageBean provideMessage() {
        return new MessageBean("hello world!");
    }
}
