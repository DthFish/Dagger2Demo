package com.dthfish.dagger2android.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Subcomponent(modules = {MainModule.class})
public interface MainSubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
