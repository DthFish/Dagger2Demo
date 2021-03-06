package com.zlz.dagger2demo.dagger.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.R;
import com.zlz.dagger2demo.dagger.bean.D;
import com.zlz.dagger2demo.dagger.component.DaggerDComponent;
import com.zlz.dagger2demo.dagger.module.DModule;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/19.
 */

public class FirstActivity extends BaseActivity implements View.OnClickListener {
    @Named("default")
    @Inject
    D mD;
    @Named("custom")
    @Inject
    D mD2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initEvent();
        DaggerDComponent.builder().dModule(new DModule("I am D")).build().inject(this);
        Log.d("tag", "mD is name ? : " + mD.getName());//
        Log.d("tag", "mD2 is name ? : " + mD2.getName());//
    }

    private void initEvent() {
        findViewById(R.id.btn_okhttp_single).setOnClickListener(this);
        findViewById(R.id.btn_detail).setOnClickListener(this);
        findViewById(R.id.btn_single_one).setOnClickListener(this);
        findViewById(R.id.btn_single_two).setOnClickListener(this);
        findViewById(R.id.btn_binds_instance).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_okhttp_single:
                startActivity(new Intent(FirstActivity.this,OkHttpSingleActivity.class));
                break;
            case R.id.btn_detail:
                startActivity(new Intent(FirstActivity.this,DetailActivity.class));
                break;
            case R.id.btn_single_one:
                startActivity(new Intent(FirstActivity.this,SingleOneActivity.class));
                break;
            case R.id.btn_single_two:
                startActivity(new Intent(FirstActivity.this,SingleTwoActivity.class));
                break;
            case R.id.btn_binds_instance:
                startActivity(new Intent(FirstActivity.this,BindsInstanceActivity.class));
                break;
        }
    }
}
