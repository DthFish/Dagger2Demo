package com.zlz.dagger2demo.dagger.module;

import com.zlz.dagger2demo.dagger.bean.ActivitySingle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zlz on 2017/4/9.
 */
@Module
public class SingleModule {
    @Singleton
    @Provides
    ActivitySingle provideActivitySingle(){

        return new ActivitySingle();
    }
}
