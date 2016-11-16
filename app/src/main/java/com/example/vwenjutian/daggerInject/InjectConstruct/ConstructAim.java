package com.example.vwenjutian.daggerInject.InjectConstruct;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/16.
 */

public class ConstructAim {

    @Inject
    ConStructA conStructA;

    public ConstructAim() {
        //真正注入的时机是在构造放法里面
        DaggerConstructComponent.create().inject(this);
        System.out.println("ccc" + conStructA.hashCode());
    }

    public static void main(String[] args) {
        new ConstructAim();
    }
}
