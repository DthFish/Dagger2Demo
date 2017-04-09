package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.module.SingleModule;
import com.zlz.dagger2demo.dagger.view.SingleOneActivity;
import com.zlz.dagger2demo.dagger.view.SingleTwoActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zlz on 2017/4/9.
 */
@Singleton
@Component(modules = {SingleModule.class})
public interface SingleComponent {

    void inject(SingleOneActivity activity);
    void inject(SingleTwoActivity activity);

}
