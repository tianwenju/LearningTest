package com.example.vwenjutian.Dragger2.DraggerThrid;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

public class Salad {

    @Inject
    @Type("nomal")
    Apple nomalApple;

    @Type("nomal")
    @Inject
    Apple nomalApple2;

    @Type("color")
    @Inject
    Apple colorApple;

    public Salad() {
        SaladComponent saladComponent = DaggerSaladComponent.create();
        saladComponent.inject(this);
        System.out.println(nomalApple.hashCode()+"_______"+nomalApple2.hashCode());

    }


    public static void main(String[] args) {
        new Salad();
    }
}

