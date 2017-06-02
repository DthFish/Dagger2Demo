package com.zlz.dagger2demo.dagger.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.R;
import com.zlz.dagger2demo.dagger.bean.D;
import com.zlz.dagger2demo.dagger.component.DaggerBindsInstanceComponent;


/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/2.
 */

public class BindsInstanceActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binds_instance);

        final D dd = DaggerBindsInstanceComponent.builder().userName("haha").build().provideD();
        final TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(dd.getName());
    }

}
