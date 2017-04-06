package com.zlz.dagger2demo.dagger.component;

import com.zlz.dagger2demo.dagger.PerActivity;
import com.zlz.dagger2demo.dagger.view.SecondActivity;

import dagger.Subcomponent;

/**
 * Description ${继承方式2，可以说是拓展}
 * Author zlz
 * Date 2017/1/22.
 */
@PerActivity
@Subcomponent
public interface SecondComponent {

    void inject(SecondActivity secondActivity);
}
