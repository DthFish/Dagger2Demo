package com.dthfish.dagger2android.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dthfish.dagger2android.R;
import com.dthfish.dagger2android.main.home.HomeFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentAndroidInjector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        getSupportFragmentManager().beginTransaction().add(R.id.fl,new HomeFragment()).commitAllowingStateLoss();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentAndroidInjector;
    }
}
