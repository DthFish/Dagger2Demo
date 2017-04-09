package com.zlz.dagger2demo.dagger.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zlz.dagger2demo.R;
import com.zlz.dagger2demo.dagger.bean.ActivitySingle;
import com.zlz.dagger2demo.dagger.component.DaggerSingleComponent;

import javax.inject.Inject;

public class SingleOneActivity extends AppCompatActivity {

    private TextView mTv1;
    private TextView mTv2;
    @Inject
    ActivitySingle mActivitySingle1;
    @Inject
    ActivitySingle mActivitySingle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("SingleOneActivity");
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        DaggerSingleComponent.create().inject(this);
        mTv1.setText(mActivitySingle1.toString());
        mTv2.setText(mActivitySingle2.toString());
    }
}
