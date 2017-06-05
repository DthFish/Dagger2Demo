package com.dthfish.dagger2android;

import android.app.Activity;

import com.dthfish.dagger2android.main.MainActivity;
import com.dthfish.dagger2android.main.MainSubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */
@Module(subcomponents = {MainSubcomponent.class})
public abstract class AppModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(MainSubcomponent.Builder builder);
}
