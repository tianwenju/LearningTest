package com.example.vwenjutian.learningtest.DraggerSecond;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Salad {
    @Inject Knife knife;
    @Inject Orange orange;

    public Salad() {
        SaladComponet saladComponet = DaggerSaladComponet.create();
        saladComponet.inject(this);
        makeSalad(knife, orange);
    }

    private void makeSalad(Knife knife, Orange orange) {
        System.out.println("加了橘子的沙拉");
    }
}
