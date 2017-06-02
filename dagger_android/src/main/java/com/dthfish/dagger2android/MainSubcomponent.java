package com.dthfish.dagger2android;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Subcomponent
public interface MainSubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }
}
