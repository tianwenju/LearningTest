package com.example.vwenjutian.Dagger2.DraggerQualifier;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Apple {
    private String color;

    public Apple() {

        System.out.println("nomal ");
    }

    public Apple(String color) {
        this.color = color;
        System.out.println("color" + color);
    }
}