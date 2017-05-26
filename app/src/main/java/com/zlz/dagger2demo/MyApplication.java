package com.zlz.dagger2demo;

import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;


/**
 * Description ${对于App生命周期中的单例}
 * Author zlz
 * Date 2017/1/20.
 */

public class MyApplication extends MultiDexApplication {
    @Inject
    AppComponent mAppComponent;

    private static MyApplication sInstance;

    public static MyApplication get(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        DaggerAppComponent.create().inject(this);
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
