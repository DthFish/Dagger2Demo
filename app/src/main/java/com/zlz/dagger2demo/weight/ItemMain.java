package com.zlz.dagger2demo.weight;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2016/10/18.
 */

public class ItemMain {
    //条目类型 标题属性
    public int type;

    //类型0
    public String title;
    public int iconType;


    //类型1
    public String traffic;

    //类型2
    public String hotel;

    //类型3
    public String day;

    /**
     *
     * @param type 类型
     * @param title 类型0 标题
     * @param iconType 类型0 icon
     * @param traffic 类型1 交通工具
     * @param hotel 类型2 酒店
     * @param day 类型3 每日行程
     */
    public ItemMain(int type, String title, int iconType, String traffic, String hotel, String day) {
        this.type = type;
        this.title = title;
        this.iconType = iconType;
        this.traffic = traffic;
        this.hotel = hotel;
        this.day = day;
    }
}
