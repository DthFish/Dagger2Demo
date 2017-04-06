package com.zlz.dagger2demo.dagger.module;

import com.zlz.dagger2demo.dagger.bean.A;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description ${单例}
 * Author zlz
 * Date 2017/1/18.
 */
@Module
public class AModule {


    @Singleton
    @Provides
    A providerA(){
        return new A();
    }
}
