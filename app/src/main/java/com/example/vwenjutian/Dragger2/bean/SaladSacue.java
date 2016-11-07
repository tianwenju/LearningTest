package com.example.vwenjutian.Dragger2.bean;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class SaladSacue {
    private static final String TAG = "SaladSacue";

    public SaladSacue() {

    }


    @Inject
    public SaladSacue(@Named("taste") String string) {
        // Log.d(TAG, "SaladSacue() called");
        System.out.println("这是" + string + "SaladSacue");
    }
}
