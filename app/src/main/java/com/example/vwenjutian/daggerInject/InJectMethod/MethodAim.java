package com.example.vwenjutian.daggerInject.InJectMethod;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/16.
 */

public class MethodAim {

    public MethodAim() {
        DaggerMethodComponent.create().inject(this);
    }

    //会初始化参数

    /**
     * 这个方法总是在实例化对象之后调用
     *
     * @param methodA
     */
    @Inject
    void setData(MethodA methodA) {

        System.out.println(methodA.hashCode() + "method");
    };

    public static void main(String[] args) {
        new MethodAim();
    }
}
