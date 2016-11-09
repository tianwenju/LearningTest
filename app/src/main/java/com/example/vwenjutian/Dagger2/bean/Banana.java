package com.example.vwenjutian.Dagger2.bean;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Banana {
    private static final String TAG = "Banana";

    private String from;

    public Banana() {

        System.out.println("这是Banana");
    }

    public Banana(String from) {
        this.from = from;
        System.out.println("这是产自" + from + "的香蕉");
    }


}
