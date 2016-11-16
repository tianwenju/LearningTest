package com.example.vwenjutian.daggerInject;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */

public class B {
    @Inject
    A a;

    @Inject
    public B() {

        System.out.println("This is b!");
    }

    public String useA() {
        return  a.useString();
    }
}
