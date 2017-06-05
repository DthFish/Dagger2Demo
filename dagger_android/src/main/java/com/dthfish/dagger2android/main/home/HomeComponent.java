package com.dthfish.dagger2android.main.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/5.
 */
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent extends AndroidInjector<HomeFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeFragment>{

    }
}
