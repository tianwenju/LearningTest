package com.example.vwenjutian.Dragger2.DraggerSecond;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Salad {
    @Inject Orange orange;

    public Salad() {
        SaladComponet saladComponet = DaggerSaladComponet.create();
        saladComponet.inject(this);
        makeSalad(orange);
    }

    public static void main(String[] args) {
        new Salad();
    }
    private void makeSalad(Orange orange) {
        System.out.println("加了橘子的沙拉");
    }
}
