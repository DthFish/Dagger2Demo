package com.zlz.dagger2demo;

import com.zlz.dagger2demo.dagger.component.SecondComponent;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/22.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    SecondComponent plus(/*DModule dModule*/);
    OkHttpClient getOKHttpClient();

}
