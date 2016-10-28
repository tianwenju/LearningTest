package com.example.vwenjutian.learningtest.Raw;

import android.util.Log;

import com.example.vwenjutian.learningtest.bean.Banana;
import com.example.vwenjutian.learningtest.bean.Pear;
import com.example.vwenjutian.learningtest.bean.SaladSacue;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Salad {
    private Banana banana;
    private SaladSacue saladSauce;
    private Pear pear;

    public Salad() {
//        这里new了三个水果对象（依赖）
        pear = new Pear();
        banana = new Banana();
        saladSauce = new SaladSacue();
        makeSalad(pear, banana, saladSauce);
    }

    private void makeSalad(Pear pear, Banana banana, SaladSacue saladSauce) {
        Log.e("TAG", "我在搅拌制作水果沙拉");
    }
}
