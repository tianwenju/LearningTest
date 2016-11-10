package com.example.vwenjutian.Dagger2.bean;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class keyboard {
    private static final String TAG = "keyboard";

    private String from;

    public keyboard() {

        System.out.println("这是键盘");
    }

    public keyboard(String from) {
        this.from = from;
        System.out.println("这是产自" + from + "的键盘");
    }

}
