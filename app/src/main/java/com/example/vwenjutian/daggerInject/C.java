package com.example.vwenjutian.daggerInject;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */

public class C {
    @Inject
    B b;

    public C() {
        DaggerCComponent.create().inject(this);
        System.out.println(b.hashCode());
    }

    /**
     * 方法注入
     * @param b
     */
    @Inject
    void setData(B b){

        System.out.println(b.hashCode());
    }
    public static void main(String[] args) {
        System.out.println(new C().b.useA());
    }
}
