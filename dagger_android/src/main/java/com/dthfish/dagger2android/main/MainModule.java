package com.dthfish.dagger2android.main;

import android.support.v4.app.Fragment;

import com.dthfish.dagger2android.main.home.HomeComponent;
import com.dthfish.dagger2android.main.home.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/5.
 */
@Module(subcomponents = {HomeComponent.class})
public abstract class MainModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeFragmentInjectorFactor(HomeComponent.Builder builder);
}
