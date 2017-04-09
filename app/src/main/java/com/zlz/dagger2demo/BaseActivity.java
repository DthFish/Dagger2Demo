package com.zlz.dagger2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zlz.dagger2demo.dagger.bean.B;
import com.zlz.dagger2demo.dagger.bean.C;
import com.zlz.dagger2demo.dagger.component.BaseComponent;
import com.zlz.dagger2demo.dagger.component.DaggerBaseComponent;

import javax.inject.Inject;

/**
 * Description ${简单的使用}
 * Author zlz
 * Date 2017/1/19.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    @Inject
    C mC;
    @Inject
    B mB1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseComponent baseComponent = DaggerBaseComponent.create();
        baseComponent.inject(this);



//        AComponent aComponent = DaggerAComponent.create();
//        aComponent.inject(this);
        Log.d(TAG, "mC == mBaseComponent.getC() ? : " + (mC == baseComponent.getC()));//false
        Log.d(TAG, "mB1 ? : " + mB1);//

    }
}
