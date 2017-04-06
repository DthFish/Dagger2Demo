package com.zlz.dagger2demo.dagger.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.R;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/19.
 */

public class FirstActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.btn_second).setOnClickListener(this);
        findViewById(R.id.btn_detail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_second:
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
                break;

            case R.id.btn_detail:
                startActivity(new Intent(FirstActivity.this,DetailActivity.class));
                break;
        }
    }
}
