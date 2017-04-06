package com.zlz.dagger2demo.dagger.module;

import com.zlz.dagger2demo.dagger.presenter.DetailPresenter;
import com.zlz.dagger2demo.dagger.presenter.IDetailPresenter;
import com.zlz.dagger2demo.dagger.view.IDetailView;

import dagger.Module;
import dagger.Provides;

/**
 * Description ${desc}
 * Author zlz
 * Date 2016/11/23.
 */
@Module
public class DetailModule {

    private IDetailView mView;

    public DetailModule(IDetailView view){
        mView = view;
    }
    /*@Provides
    IDetailView providerIDetailView(){
        return mView;
    }*/
    @Provides
    IDetailPresenter providerIDetailPresenter(){
//        return new DetailPresenter2(mView);
        return new DetailPresenter(mView);
    }
}
