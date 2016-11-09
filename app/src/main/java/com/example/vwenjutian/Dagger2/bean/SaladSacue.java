package com.example.vwenjutian.Dagger2.bean;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class SaladSacue {

    public SaladSacue() {
    }
    @Inject
    public SaladSacue(@Named("taste") String string) {
        System.out.println("这是" + string + "SaladSacue");

    }
}
