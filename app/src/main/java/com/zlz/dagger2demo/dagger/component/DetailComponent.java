package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.view.DetailActivity;
import com.zlz.dagger2demo.dagger.PerActivity;
import com.zlz.dagger2demo.dagger.module.DetailModule;

import dagger.Component;

/**
 * Description ${继承方式1}
 * Author Zhaolizhi
 * Date 2016/11/23.
 */
@PerActivity//因为AComponent用了@Singleton所以此处不能继续用@Singleton
@Component(dependencies = {AComponent.class},modules = {DetailModule.class})
public interface DetailComponent {
    void inject(DetailActivity activity);
}
