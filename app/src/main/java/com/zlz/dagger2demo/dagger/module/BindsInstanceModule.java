package com.zlz.dagger2demo.dagger.module;

import com.zlz.dagger2demo.dagger.bean.D;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Module
public class BindsInstanceModule {
    @Provides
    D provideD(@Named("dd") String name){
        return new D(name);
    }
}
