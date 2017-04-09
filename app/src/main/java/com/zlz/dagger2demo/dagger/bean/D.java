package com.zlz.dagger2demo.dagger.bean;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/4/7.
 */

public class D {
    private String name;

    public D(String name) {
        this.name = name;
    }
    public D(){
        this.name = "I am default D";
    }

    public String getName() {
        return name;
    }
}
