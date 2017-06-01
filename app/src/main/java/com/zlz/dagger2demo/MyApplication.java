package com.zlz.dagger2demo;

import android.support.multidex.MultiDexApplication;

import com.zlz.dagger2demo.dagger.Test;
import com.zlz.dagger2demo.dagger.bean.E;

import javax.inject.Inject;

import dagger.releasablereferences.ForReleasableReferences;
import dagger.releasablereferences.ReleasableReferenceManager;


/**
 * Description ${对于App生命周期中的单例}
 * Author zlz
 * Date 2017/1/20.
 */

public class MyApplication extends MultiDexApplication {
    @Inject
    AppComponent mAppComponent;
    @Inject
    @ForReleasableReferences(Test.class)
    ReleasableReferenceManager mReleasableReferenceManager;
    @Inject
    E mE;
    private static MyApplication sInstance;

    public static MyApplication get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        DaggerAppComponent.create().inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mReleasableReferenceManager.releaseStrongReferences();
    }
}
