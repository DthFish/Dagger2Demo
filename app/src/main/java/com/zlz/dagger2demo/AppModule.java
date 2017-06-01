package com.zlz.dagger2demo;

import com.zlz.dagger2demo.dagger.Test;
import com.zlz.dagger2demo.dagger.bean.E;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/22.
 */
@Module/*(subcomponents = {OkHttpSingleComponent.class})*/
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {

        return new OkHttpClient.Builder().connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }
    @Provides
    Interceptor provideCommonParamsInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                final HttpUrl newUrl = request.url().newBuilder()
                        .addQueryParameter("channel", "android")
                        .addQueryParameter("version", "1.0.0")
                        .build();
                Request newRequest = request.newBuilder().url(newUrl).build();
                return chain.proceed(newRequest);
            }
        };
    }

    @Test
    @Provides
    E provideE(){
        return new E();
    }

}
