package com.zlz.dagger2demo.dagger.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.MyApplication;
import com.zlz.dagger2demo.R;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/19.
 */

public class SecondActivity extends BaseActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    @Inject
    OkHttpClient mOkHttpClient;
    @Inject
    OkHttpClient mOkHttpClient2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MyApplication.get().getAppComponent().plus(/*new DModule("dd")*/).inject(this);
        Log.d(TAG, "mOkHttpClient == mOkHttpClient2 ? : " + (mOkHttpClient == mOkHttpClient2));//true

    }
}
