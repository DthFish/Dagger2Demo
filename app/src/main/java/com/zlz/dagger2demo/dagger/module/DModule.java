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
    D provideD(){
        Log.d("tag", "provideD");
        return new D(mDName);
    }
    @Named("default")
    @Provides
    D provideDefaultD() {

        return new D();
    }

    @Provides
    String provideName(){
        Log.d("tag", "provideName");
        return mDName;
    }
}
