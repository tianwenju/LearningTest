package com.example.vwenjutian.daggerInject;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */

public class A {

    @Inject
    public A() {
        System.out.println("aaaaaa");
    }


    public String useString() {
        return "aaaaaaa+ useString";
    }
}
