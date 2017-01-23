package com.zlz.dagger2demo.dagger.presenter;

import com.zlz.dagger2demo.weight.ItemMain;
import com.zlz.dagger2demo.dagger.view.IDetailView;

import java.util.ArrayList;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2016/11/23.
 */

public class DetailPresenter2 implements IDetailPresenter {
    IDetailView mView;

    //@Inject
    public DetailPresenter2(IDetailView view) {
        mView = view;
    }

    @Override
    public void start() {
        ArrayList<ItemMain> datas = new ArrayList<>();

//        datas.add(new ItemMain(0, "交通出行", 0, null, null, null));
//        datas.add(new ItemMain(1, null, -1, "1", null, null));
//        datas.add(new ItemMain(1, null, -1, "2", null, null));
//        datas.add(new ItemMain(1, null, -1, "3", null, null));
        datas.add(new ItemMain(0, "酒店住宿", 1, null, null, null));
        datas.add(new ItemMain(2, null, -1, null, "1", null));
        datas.add(new ItemMain(2, null, -1, null, "1", null));
        datas.add(new ItemMain(2, null, -1, null, "1", null));

        ItemMain dailyTitle = new ItemMain(0, "行程参考", 2, null, null, null);
        datas.add(dailyTitle);

        for (int i = 0; i < 10; i++) {
            datas.add(new ItemMain(3, null, -1, null, null, "" + i));
        }


        int dailyTitleIndex = datas.indexOf(dailyTitle);
        mView.setDatas(datas);
        mView.setDailyTitleIndex(dailyTitleIndex);


        int rbCount = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).type == 3) {
                rbCount++;
            }
        }
        if(rbCount >0){
            mView.initRadioButton(rbCount);
        }
    }
}
