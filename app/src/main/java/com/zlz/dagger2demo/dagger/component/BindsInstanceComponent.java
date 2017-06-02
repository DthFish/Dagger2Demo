package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.bean.D;
import com.zlz.dagger2demo.dagger.module.BindsInstanceModule;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Component(modules = {BindsInstanceModule.class})
public interface BindsInstanceComponent {
//    void inject(BindsInstanceActivity activity);
    D provideD();
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder userName(@Named("dd") String userName);
        BindsInstanceComponent build();
    }
}
