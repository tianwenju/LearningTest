package com.example.vwenjutian.Dragger2.DraggerSix;

import com.example.vwenjutian.Dragger2.bean.Banana;
import com.example.vwenjutian.Dragger2.bean.Pear;
import com.example.vwenjutian.Dragger2.bean.SaladSacue;
import com.example.vwenjutian.Dragger2.draggerFirst.SaladModule;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

public class Salad {

    @Inject
    Tomato tomato;
    @Inject
    Banana banana;
    @Inject
    Pear pear;
    @Inject
    SaladSacue saladSacue;

    public Salad() {
        TomatoComponent tomatoComponent = DaggerTomatoComponent.create();
        DaggerSaladComponet.builder().saladModule(new SaladModule()).tomatoComponent(tomatoComponent).build().inject(this);

    }

    public static void main(String[] args) {
        new Salad();
    }
}
