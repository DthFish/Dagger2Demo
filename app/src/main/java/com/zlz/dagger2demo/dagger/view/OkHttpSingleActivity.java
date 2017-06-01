package com.zlz.dagger2demo.dagger.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

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

public class OkHttpSingleActivity extends BaseActivity {
    private static final String TAG = OkHttpSingleActivity.class.getSimpleName();
    @Inject
    OkHttpClient mOkHttpClient;
    @Inject
    OkHttpClient mOkHttpClient2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_single);
        MyApplication.get().getAppComponent().plus().inject(this);//方法1
//        MyApplication.get().getAppComponent().sComponent().build().inject(this);
//        DaggerOkHttpSingle2Component.builder().appComponent(MyApplication.get().getAppComponent()).build().inject(this);//方法2
        TextView tvTime = (TextView) findViewById(R.id.tv_time);
        tvTime.setText("time:" + System.currentTimeMillis());
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("mOkHttpClient == mOkHttpClient2 ? " + (mOkHttpClient == mOkHttpClient2) + "\n" +
                mOkHttpClient.toString());
        Log.d(TAG, "mOkHttpClient == mOkHttpClient2 ? : " + (mOkHttpClient == mOkHttpClient2));//true

    }
}
