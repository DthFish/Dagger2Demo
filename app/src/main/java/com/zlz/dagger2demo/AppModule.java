package com.zlz.dagger2demo;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/22.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder().connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .build();
}

}
