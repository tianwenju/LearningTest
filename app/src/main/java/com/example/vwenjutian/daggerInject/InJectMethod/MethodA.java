package com.example.vwenjutian.daggerInject.InJectMethod;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/16.
 */

public class MethodA {

    @Inject
    public MethodA() {
        System.out.println("this is MethodA");
    }
}
