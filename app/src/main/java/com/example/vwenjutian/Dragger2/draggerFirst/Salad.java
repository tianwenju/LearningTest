package com.example.vwenjutian.Dragger2.draggerFirst;

import com.example.vwenjutian.Dragger2.bean.Banana;
import com.example.vwenjutian.Dragger2.bean.Pear;
import com.example.vwenjutian.Dragger2.bean.SaladSacue;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Salad {
    @Inject
    Banana banana;
    @Inject
    Pear pear;
    @Inject
    SaladSacue saladSacue;

    @Inject
    Peach peach;
    public Salad() {
        DaggerSaladComponent.create().inject(this);
        makeSalad(banana, pear,saladSacue);
    }

    private void makeSalad(Banana banana, Pear pear, SaladSacue saladSacue) {
        System.out.println("制造完成");

    }
    public static void main(String[] args) {

         new Salad();
    }
}
