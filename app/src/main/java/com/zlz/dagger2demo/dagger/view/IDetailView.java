package com.zlz.dagger2demo.dagger.view;

import com.zlz.dagger2demo.weight.ItemMain;

import java.util.List;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2016/11/23.
 */
public interface IDetailView {
    void setDailyTitleIndex(int index);
    void initRadioButton(int count);
    void setData(List<ItemMain> datas);
}
