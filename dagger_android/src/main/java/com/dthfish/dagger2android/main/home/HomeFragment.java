package com.dthfish.dagger2android.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dthfish.dagger2android.R;
import com.dthfish.dagger2android.bean.MessageBean;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2017/6/5.
 */

public class HomeFragment extends Fragment {
    @Inject
    MessageBean mMessageBean;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(mMessageBean.getMsg());
    }
}
