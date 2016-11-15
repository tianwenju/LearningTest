package com.example.vwenjutian.Dagger2.DaggerQualifier;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Mouse {
    private String color;

    public Mouse() {

        System.out.println("nomal ");
    }

    public Mouse(String color) {
        this.color = color;
        System.out.println("color" + color);
    }
}
