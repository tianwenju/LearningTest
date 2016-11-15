package com.example.vwenjutian.daggerInject;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */

public class Tea {
    @Inject
    A a;

    @Inject
    public Tea() {

        System.out.println("This is tea!");
    }

    public String useA() {
        return  a.useString();
    }
}
