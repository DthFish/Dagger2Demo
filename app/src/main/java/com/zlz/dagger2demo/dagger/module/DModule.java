package com.zlz.dagger2demo.dagger.module;

import android.util.Log;

import com.zlz.dagger2demo.dagger.bean.D;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/4/7.
 */
@Module
public class DModule {

    private String mDName;

    public DModule(String dName) {
        mDName = dName;
    }
    @Named("custom")
    @Provides
    D providerD(){
        Log.d("tag", "providerD");
        return new D(mDName);
    }
    @Named("default")
    @Provides
    D providerDefaultD() {

        return new D();
    }

    @Provides
    String providerName(){
        Log.d("tag", "providerName");
        return mDName;
    }
}
